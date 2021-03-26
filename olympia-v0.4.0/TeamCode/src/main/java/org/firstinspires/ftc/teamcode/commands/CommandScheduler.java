package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommandScheduler {

    private enum commandPriority {
        LOW,
        HIGH
    }
    private Map<Mechanism, ArrayList<Command>> mechanismBindingMap = new HashMap<Mechanism, ArrayList<Command>>();
    private Map<Command, Boolean> boundCommandRunning = new HashMap<Command, Boolean>();
    private Map<Command, commandPriority> boundCommandPriority = new HashMap<Command, commandPriority>();

    private ArrayList<Command> commandList = new ArrayList<>();
    private ArrayList<Boolean> commandInitializedList = new ArrayList<>();

    public void add(Command command) {
        commandList.add(command);
        commandInitializedList.add(false);

        if (!command.getBoundMechanisms().isEmpty()) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                if (mechanismBindingMap.containsKey(mechanism)) {
                    mechanismBindingMap.get(mechanism).add(command);
                } else {
                    mechanismBindingMap.put(mechanism, (ArrayList)Arrays.asList(command));
                }

                boundCommandRunning.put(command, false);
                boundCommandPriority.put(command, commandPriority.LOW);
            }
        }
    }

    public void addHighPriority(Command command) {
        commandList.add(command);
        commandInitializedList.add(false);

        if (!command.getBoundMechanisms().isEmpty()) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                if (mechanismBindingMap.containsKey(mechanism)) {
                    mechanismBindingMap.get(mechanism).add(command);
                } else {
                    mechanismBindingMap.put(mechanism, (ArrayList)Arrays.asList(command));
                }

                boundCommandRunning.put(command, false);
                boundCommandPriority.put(command, commandPriority.HIGH);
            }
        }
    }

    public void run() {

        if (!commandList.isEmpty()) {
            for (Command command : commandList) {

                if (commandInitializedList.get(commandList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitializedList.set(commandList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandInitializedList.remove(commandList.indexOf(command));
                    commandList.remove(command);
                }
            }
        }
    }

    public void end() {
        for (Command command : commandList) {
            command.end();
        }
    }

    public boolean isEmpty() {
        return commandList.isEmpty();
    }

    public void postCommands(Telemetry telemetry) {
        for (Command command : commandList) {
            telemetry.addData(command.toString(), "Initialized");
        }
    }
}
