package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TestIntake;

public class RetractIntake extends Command {
    private TestIntake localIntake = MechanismEngine.getInstance().getMechanism(TestIntake.class);

    public RetractIntake() {
        Requires(localIntake);
    }

    public void initialize() {

    }

    public void execute() {
        localIntake.retract();
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {

    }
}
