package org.firstinspires.ftc.teamcode.commands.basecommands;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;

import java.util.ArrayList;

public class SteppedCommand implements Command {

    private ArrayList<Command> commandList;
    private ArrayList<Boolean> initialized;

    public SteppedCommand(ArrayList<Command> inputList) {
        this.commandList = inputList;

        for (int index = 0; index < inputList.size(); index++) {
            this.initialized.set(index, false);
        }
    }

    public void initialize() {
        if (initialized.get(0) == false) {
            commandList.get(0).initialize();
            initialized.set(0, true);
        }
    }

    public void execute() {
        initialize();

        if (commandList.get(0).isFinished()) {
            commandList.get(0).end();

            commandList.remove(0);
            initialized.remove(0);
        } else {
            commandList.get(0).execute();
        }
    }

    public boolean isFinished() {
        return commandList.isEmpty();
    }

    public void end() {
        commandList.get(0).end();

        commandList.clear();
        initialized.clear();
    }
}
