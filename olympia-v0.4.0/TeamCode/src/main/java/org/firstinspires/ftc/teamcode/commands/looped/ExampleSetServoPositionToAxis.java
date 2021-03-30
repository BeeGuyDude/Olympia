package org.firstinspires.ftc.teamcode.commands.looped;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleSetServoPositionToAxis extends Command {
    private ExampleServoMechanism localIntake = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);
    //We have a private axis reference here, as we need something to read from.
    private Axis inputAxis;

    //Note the fact that in this constructor, not only do we have a mechanism locking Requires() call, but also a passthrough for an axis.
    public ExampleSetServoPositionToAxis(Axis inputAxis) {
        Requires(localIntake);

        this.inputAxis = inputAxis;
    }

    public void initialize() {

    }

    //Note the fact that we don't just give it a double input, we actually use the value of the axis actively with the .get() method.
    //Think about using multiple axes for a Drive command or something similar.
    public void execute() {
        localIntake.setPosition(inputAxis.get());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }
}
