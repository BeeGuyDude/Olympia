package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TestIntake;

public class TestCommand implements Command {

    private TestIntake localMechanism = MechanismEngine.getInstance().getMechanism(TestIntake.class);

    public void initialize() {

    }

    public void execute() {
        localMechanism.extend();
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }

}
