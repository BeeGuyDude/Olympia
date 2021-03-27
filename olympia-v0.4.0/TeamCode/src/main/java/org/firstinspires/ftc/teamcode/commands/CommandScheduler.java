package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CommandScheduler {

    private enum commandPriority {
        LOW,
        HIGH
    }

    private ArrayList<Command> rawCommandList = new ArrayList<Command>();
    private Map<Command, commandPriority> boundCommandPriority = new HashMap<Command, commandPriority>();

    private Map<Mechanism, ArrayList<Command>> mechanismBindingMap = new HashMap<Mechanism, ArrayList<Command>>();

    private ArrayList<Command> commandExecutionList = new ArrayList<Command>();
    private ArrayList<Boolean> commandInitializedList = new ArrayList<Boolean>();

    private Map<Command, Command> commandTriggeredEndingMap = new HashMap<Command, Command>();

    public void add(Command command) {
        rawCommandList.add(command);

        if (!command.getBoundMechanisms().isEmpty()) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                if (mechanismBindingMap.containsKey(mechanism)) {
                    mechanismBindingMap.get(mechanism).add(command);
                } else {
                    mechanismBindingMap.put(mechanism, new ArrayList(Arrays.asList(command)));
                }

                boundCommandPriority.put(command, commandPriority.LOW);
            }
        }
    }

    public void addHighPriority(Command command) {
        rawCommandList.add(command);
        commandInitializedList.add(false);

        if (!command.getBoundMechanisms().isEmpty()) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                if (mechanismBindingMap.containsKey(mechanism)) {
                    mechanismBindingMap.get(mechanism).add(command);
                } else {
                    mechanismBindingMap.put(mechanism, new ArrayList(Arrays.asList(command)));
                }

                boundCommandPriority.put(command, commandPriority.HIGH);
            }
        }
    }

    public void run() {

        if (!rawCommandList.isEmpty()) {
            for (Command command : rawCommandList) {

                if (commandInitializedList.get(rawCommandList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitializedList.set(rawCommandList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandInitializedList.remove(rawCommandList.indexOf(command));
                    rawCommandList.remove(command);
                }
            }
        }
    }

    public void updatedRun() {
        if (!rawCommandList.isEmpty()) {

            for (Command command : rawCommandList) {
                boolean passedCheck = true;

                if (command.getBoundMechanisms().isEmpty()) {
                    if (commandExecutionList.contains(command)) {
                        passedCheck = false;
                    }
                } else {
                    for (Command cachedCommand : commandExecutionList) {
                        for (Mechanism mechanism : cachedCommand.getBoundMechanisms()) {
                            if (command.getBoundMechanisms().contains(mechanism)) {
                                if (boundCommandPriority.get(command) == boundCommandPriority.get(cachedCommand) || boundCommandPriority.get(command) == commandPriority.HIGH) { //Successful Interrupt
                                    commandTriggeredEndingMap.put(command, cachedCommand);
                                } else { //Failed Interrupt
                                    for (Map.Entry<Command, Command> endRequestEntry : commandTriggeredEndingMap.entrySet()) {
                                        if (endRequestEntry.getKey() == command) {
                                            commandTriggeredEndingMap.remove(endRequestEntry);
                                        }
                                    }

                                    passedCheck = false;
                                }
                            }
                        }
                    }
                }

                if (passedCheck) {
                    commandExecutionList.add(command);
                    commandInitializedList.add(false);
                }
            }
        }

        if (!commandExecutionList.isEmpty()) {
            for (Command command : commandExecutionList) {

                if (commandInitializedList.get(rawCommandList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitializedList.set(rawCommandList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandInitializedList.remove(rawCommandList.indexOf(command));
                    commandExecutionList.remove(command);
                }
            }
        }
    }

    public void end() {
        for (Command command : rawCommandList) {
            command.end();
        }
    }

    public boolean isEmpty() {
        return rawCommandList.isEmpty();
    }

    public void postCommands(Telemetry telemetry) {
        for (Command command : rawCommandList) {
            telemetry.addData(command.toString(), "Initialized");
        }
    }
}
