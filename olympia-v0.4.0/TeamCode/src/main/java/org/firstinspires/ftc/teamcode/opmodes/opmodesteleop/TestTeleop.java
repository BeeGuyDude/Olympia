package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.ExampleExtendServo;
import org.firstinspires.ftc.teamcode.commands.actions.ExampleRetractServo;
import org.firstinspires.ftc.teamcode.commands.looped.ExampleSetServoPositionToAxis;

@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
//        scheduler.enableDebugTelemetry();                   //Uncomment to get the command list from hell

    }

    @Override
    public void teleOpLoop() {
        scheduler.add(new ExampleSetServoPositionToAxis(DriverRightXAxis));

        DriverAButton.whileHeld(new ExampleExtendServo());
        DriverBButton.whileHeld(new ExampleRetractServo());
    }

}

