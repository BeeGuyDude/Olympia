package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;


public class CommandScheduler {

    private enum commandType {
        LOOPED,
        SEQUENTIAL,
        PARALLEL
    }

    private ArrayList<Command> commandList;
    private ArrayList<commandType> commandTypeList;
    private ArrayList<Boolean> commandInitialized;

    public void addLooped(Command command) {
        commandList.add(0, command);
        commandTypeList.add(0, commandType.LOOPED);
        commandInitialized.add(0, false);
    }

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

    public void run() {

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

    public void end() {
        for (Command command : commandList) {
            command.end();
        }
    }

    public boolean isEmpty() {
        return commandList.isEmpty();
    }
}
