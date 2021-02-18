package org.firstinspires.ftc.teamcode.commands;

import java.util.ArrayList;

public class CommandScheduler {
    private ArrayList<Command> uninitialized;
    private ArrayList<Command> loopedList;
    private ArrayList<Command> parallelList;
    private ArrayList<Command> sequentialList;

    public void addLooped(Command command) {
        uninitialized.add(command);
        loopedList.add(command);
    }

    public void addParallel(Command command) {
        uninitialized.add(command);
        parallelList.add(command);
    }

    public void addSequential(Command command) {
        sequentialList.add(command);
    }

    public void run() {
        for (Command command : uninitialized) {
            command.initialize();
            uninitialized.remove(command);
        }

        for (Command command : loopedList) {
            command.execute();
        }

    }

    public void end() {
        for (Command command : loopedList) {
            command.end();
            parallelList.remove(command);
        }

    }
}
