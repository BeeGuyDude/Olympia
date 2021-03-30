package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.ExampleExtendServo;
import org.firstinspires.ftc.teamcode.commands.actions.ExampleRetractServo;
import org.firstinspires.ftc.teamcode.commands.looped.ExampleSetServoPositionToAxis;

//This little annotation thingy is important, it's what makes the OpMode come up on the phone
@TeleOp
public class ExampleTeleOpMode extends TeleOpModeWrapper {      //Note that you extend the wrapper, not just an OpMode

    //Commands to run when you press the init button go here; **FOR THE LOVE OF WAN DO NOT ADD A COMMAND THAT RETURNS [isFinished -> false] HERE, IT RUNS COMMANDS UNTIL THEY FINISH**
    @Override
    public void teleOpInit() {
//        scheduler.enableDebugTelemetry();
        scheduler.add(new ExampleRetractServo());

    }

    //Commands to run repeatedly when you press the play button go here; see below for examples of commands to use.
    @Override
    public void teleOpLoop() {
        scheduler.add(new ExampleSetServoPositionToAxis(DriverRightXAxis));

        DriverAButton.whileHeld(new ExampleExtendServo());
        DriverBButton.whileHeld(new ExampleRetractServo());
    }
}

