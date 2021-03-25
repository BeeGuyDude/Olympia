package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TestIntake;

public class SetIntakePosition implements Command {
    private TestIntake localIntake = MechanismEngine.getInstance().include(TestIntake.class);

    private Axis inputAxis;

    public SetIntakePosition(Axis inputAxis) {
        this.inputAxis = inputAxis;
    }

    public void initialize() {

    }

    public void execute() {
        localIntake.setPosition(inputAxis.get());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }
}
