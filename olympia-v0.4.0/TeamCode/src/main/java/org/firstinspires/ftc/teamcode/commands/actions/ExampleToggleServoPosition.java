package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleToggleServoPosition extends Command {

    private ExampleServoMechanism localIntake = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);

    public void initialize() {

    }

    public void execute() {
        localIntake.toggle();
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {

    }
}
