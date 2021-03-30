package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleExtendServo extends Command {
    private ExampleServoMechanism localServoMechanism = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);

    public ExampleExtendServo() {
        Requires(localServoMechanism);
    }

    public void initialize() {}

    public void execute() {
        localServoMechanism.extend();
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }
}
