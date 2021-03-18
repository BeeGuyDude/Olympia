package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;

import java.util.ArrayList;


public class CommandScheduler {

    private ArrayList<Command> commandList = new ArrayList<>();
    private ArrayList<Boolean> commandInitialized = new ArrayList<>();

    public void add(Command command) {
        commandList.add(command);
        commandInitialized.add(false);
    }

    public void run() {

        if (!commandList.isEmpty()) {
            for (Command command : commandList) {

                if (commandInitialized.get(commandList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitialized.set(commandList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

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
