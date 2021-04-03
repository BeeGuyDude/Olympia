package org.firstinspires.ftc.teamcode.commands.basecommands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandGroup extends Command {
    private enum commandType {
        SEQUENTIAL,
        PARALLEL
    }

    private ArrayList<Command> commandList = new ArrayList<Command>();
    private Map<Command, commandType> commandTypeMap = new HashMap<Command, commandType>();
    private Map<Command, Boolean> commandInitializedMap = new HashMap<Command, Boolean>();

    private ArrayList<Command> removedCommandList = new ArrayList<Command>();

    public void addParallel(Command command) {
        commandList.add(command);
        commandTypeMap.put(command, commandType.PARALLEL);
        commandInitializedMap.put(command, false);
    }

    public void addSequential(Command command) {
        commandList.add(command);
        commandTypeMap.put(command, commandType.SEQUENTIAL);
        commandInitializedMap.put(command, false);
    }

    public void initialize() {}

    public void execute() {
        if (!commandList.isEmpty()) {
            for (Command command : commandList) {
                if (commandList.indexOf(command) != 0) {
                    if (commandTypeMap.get(commandList.get(commandList.indexOf(command) - 1)) == commandType.SEQUENTIAL) {
                        break;
                    }
                }

                if (commandInitializedMap.get(command) == false) {
                    command.initialize();
                    commandInitializedMap.put(command, true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    removedCommandList.add(command);
                }
            }

            for (Command removedCommand : removedCommandList) {
                commandTypeMap.remove(removedCommand);
                commandInitializedMap.remove(removedCommand);
            }
            commandList.removeAll(removedCommandList);
        }
    }

    public boolean isFinished() {
        return commandList.isEmpty();
    }

    public void end() {
        for (Command command : commandList) {
            if (commandInitializedMap.get(command) == true) command.end();
        }
    }
}
