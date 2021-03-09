package org.firstinspires.ftc.teamcode.commands.basecommands;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;

import java.util.ArrayList;

public class CommandGroup implements Command {
    private enum commandType {
        SEQUENTIAL,
        PARALLEL
    }

    private ArrayList<Command> commandList;
    private ArrayList<commandType> commandTypeList;
    private ArrayList<Boolean> commandInitialized;

    public void addParallel(Command command) {
        commandList.add(command);
        commandTypeList.add(commandType.PARALLEL);
        commandInitialized.add(false);
    }

    public void addSequential(Command command) {
        commandList.add(command);
        commandTypeList.add(commandType.SEQUENTIAL);
        commandInitialized.add(false);
    }

    public void initialize() {}

    public void execute() {
        if (!commandList.isEmpty()) {
            for (Command command : commandList) {
                if (commandList.indexOf(command) != 0) {
                    if (commandTypeList.get(commandList.indexOf(command) - 1) == commandType.SEQUENTIAL) {
                        break;
                    }
                }

                if (commandInitialized.get(commandList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitialized.set(commandList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandTypeList.remove(commandList.indexOf(command));
                    commandInitialized.remove(commandList.indexOf(command));
                    commandList.remove(command);
                }
            }
        }
    }

    public boolean isFinished() {
        return commandList.isEmpty();
    }

    public void end() {
        for (Command command : commandList) {
            command.end();
        }
    }
}
