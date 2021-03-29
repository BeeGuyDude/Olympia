package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.ExtendIntake;
import org.firstinspires.ftc.teamcode.commands.actions.RetractIntake;
import org.firstinspires.ftc.teamcode.commands.actions.SetIntakePosition;
import org.firstinspires.ftc.teamcode.commands.actions.TestDrive;
import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;
import org.firstinspires.ftc.teamcode.commands.actions.TestTelemetryCommand;
import org.firstinspires.ftc.teamcode.commands.actions.ToggleIntakePosition;
import org.firstinspires.ftc.teamcode.commands.actions.UnendingToggleIntake;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;


@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        telemetry.addData("Test", "Success");
//        scheduler.add(new TestTelemetryCommand(telemetry));
    }

    @Override
    public void teleOpLoop() {
        scheduler.add(new SetIntakePosition(DriverRightXAxis));

        DriverAButton.whileHeld(new ExtendIntake());
        DriverBButton.whileHeld(new RetractIntake());
    }

    @Override
    public void teleOpStop() {

    }

}

