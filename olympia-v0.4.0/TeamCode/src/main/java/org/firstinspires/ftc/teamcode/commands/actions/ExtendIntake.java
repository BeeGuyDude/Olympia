package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TestIntake;

public class ExtendIntake implements Command {
    private TestIntake localIntake = MechanismEngine.getInstance().include(TestIntake.class);

    public void initialize() {

    }

    public void execute() {
        localIntake.extend();
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {

    }
}
