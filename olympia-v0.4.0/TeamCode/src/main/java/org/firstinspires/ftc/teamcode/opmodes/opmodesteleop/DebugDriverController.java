package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class DebugDriverController extends OpMode {
    @Override
    public void init() {}

    @Override
    public void loop() {
        telemetry.addData("A Button", gamepad1.a);
        telemetry.addData("B Button", gamepad1.b);
        telemetry.addData("X Button", gamepad1.x);
        telemetry.addData("Y Button", gamepad1.y);

        telemetry.addData("Left Bumper", gamepad1.left_bumper);
        telemetry.addData("Right Bumper", gamepad1.right_bumper);

        telemetry.addData("Back Button", gamepad1.back);
        telemetry.addData("Start Button", gamepad1.start);
        telemetry.addData("Center Button", gamepad1.guide);

        telemetry.addData("DPAD Up", gamepad1.dpad_up);
        telemetry.addData("DPAD Down", gamepad1.dpad_down);
        telemetry.addData("DPAD Left", gamepad1.dpad_left);
        telemetry.addData("DPAD Right", gamepad1.dpad_right);

        telemetry.addData("Left Stick X", gamepad1.left_stick_x);
        telemetry.addData("Left Stick Y", -gamepad1.left_stick_y);
        telemetry.addData("Right Stick X", gamepad1.right_stick_x);
        telemetry.addData("Right Stick Y", -gamepad1.right_stick_y);

        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
    }
}
