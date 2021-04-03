package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.ExampleServoMechanism;

public class ExampleRetractServo extends Command {
    //CopyPasta this line as appropriate to create your mechanism references in commands
    //Replace the getMechanism() call with "[MechanismClass].class" appropriately
    private ExampleServoMechanism localServoMechanism = MechanismEngine.getInstance().getMechanism(ExampleServoMechanism.class);

    //Mechanism locking is done with the Requires() method, specifically in the **CONSTRUCTOR** for the command.
    //Note that we pass the **LOCAL** mechanism into Requires() and not the class; this is intentional.

    public ExampleRetractServo() {
        Requires(localServoMechanism);
    }

    public void initialize() {}

    public void execute() {
        localServoMechanism.retract();
    }

    //This command returns false for isFinished() because the assumption is it's put on a toggleWhenPressed or a whileHeld like in ExampleTeleOpMode
    //If you're trying to use a whenPressed or whenReleased, make sure the finished condition actually returns true at some point, or it'll lock the mechanism indefinitely
    public boolean isFinished() {
        return false;
    }

    public void end() {

    }
}
