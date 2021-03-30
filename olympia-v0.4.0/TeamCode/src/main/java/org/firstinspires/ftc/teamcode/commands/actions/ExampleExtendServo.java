package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleExtendServo extends Command {

    //CopyPasta this line as appropriate to create your mechanism references in commands
    //Replace the getMechanism() call with "[MechanismClass].class" appropriately
    private ExampleServoMechanism localServoMechanism = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);

    //Mechanism locking is done with the Requires() method, specifically in the **CONSTRUCTOR** for the command.
    //Note that we pass the **LOCAL** mechanism into Requires() and not the class; this is intentional.
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
