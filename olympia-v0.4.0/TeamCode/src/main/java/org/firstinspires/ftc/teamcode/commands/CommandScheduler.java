package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;

import java.util.ArrayList;


public class CommandScheduler {

    private enum commandPriority {
        LOW,
        HIGH
    }

    private ArrayList<Command> commandList = new ArrayList<>();
    private ArrayList<Boolean> commandInitializedList = new ArrayList<>();
    private ArrayList<commandPriority> commandPriorityList = new ArrayList<>();

    public void add(Command command) {
        commandList.add(command);
        commandInitializedList.add(false);
        commandPriorityList.add(commandPriority.LOW);
    }

    public void addHighPriority(Command command) {
        commandList.add(command);
        commandInitializedList.add(false);
        commandPriorityList.add(commandPriority.HIGH);
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
