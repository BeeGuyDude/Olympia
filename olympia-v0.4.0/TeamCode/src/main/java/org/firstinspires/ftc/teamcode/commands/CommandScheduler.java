package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;


public class CommandScheduler {

    private ArrayList<Command> loopedInitialized;
    private ArrayList<Command> loopedList;

    private ArrayList<Command> commandList;
    private ArrayList<String> commandType;
    private ArrayList<Boolean> commandInitialized;

    public void addLooped(Command command) {
        loopedInitialized.add(command);
        loopedList.add(command);
    }

    public void addParallel(Command command) {
        commandList.add(command);
        commandType.add(PARALLEL);
        commandInitialized.add(false);
    }

    public void addSequential(Command command) {
        commandList.add(command);
        commandType.add(SEQUENTIAL);
        commandInitialized.add(false);
    }

    public void run() {

        for (Command command : loopedInitialized) {
            command.initialize();
            loopedInitialized.remove(command);
        }
        for (Command command : loopedList) {
            command.execute();
        }

        if (!commandList.isEmpty()) {
            for (Command command : commandList) {
                if (commandList.indexOf(command) != 0) {
                    if (commandType.get(commandList.indexOf(command) - 1) != PARALLEL) {
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

                    commandType.remove(commandList.indexOf(command));
                    commandInitialized.remove(commandList.indexOf(command));
                    commandList.remove(command);
                }
            }
        }
    }

    public void end() {
        for (Command command : loopedList) {
            command.end();
        }

        for (Command command : commandList) {
            command.end();
        }

    }
}
