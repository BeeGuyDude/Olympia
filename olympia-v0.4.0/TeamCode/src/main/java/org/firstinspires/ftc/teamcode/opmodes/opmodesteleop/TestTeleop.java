package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.TestDrive;
import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;
import org.firstinspires.ftc.teamcode.commands.actions.TestTelemetryCommand;
import org.firstinspires.ftc.teamcode.commands.actions.ToggleIntakePosition;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TestIntake;

@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        telemetry.addData("Test", "Success");
    }

    @Override
    public void teleOpLoop() {
//        scheduler.add(new TestDrive(OperatorLeftYAxis, OperatorRightYAxis));
//
        DriverAButton.whileHeld(new TestTelemetryCommand(telemetry));

        scheduler.postCommands(telemetry);

//        scheduler.add(new TestTelemetryCommand(telemetry));
    }

    @Override
    public void teleOpStop() {

    }

}

