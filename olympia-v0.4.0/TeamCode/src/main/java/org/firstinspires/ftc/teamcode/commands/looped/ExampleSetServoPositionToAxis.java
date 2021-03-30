package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleSetServoPositionToAxis extends Command {
    private ExampleServoMechanism localIntake = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);
    private Axis inputAxis;

    public ExampleSetServoPositionToAxis(Axis inputAxis) {
        Requires(localIntake);

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
