package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.TestDrive;
import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;
import org.firstinspires.ftc.teamcode.commands.actions.ToggleIntakePosition;

@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        telemetry.addData("Test", "Success");

    }

    @Override
    public void teleOpLoop() {
        scheduler.add(new TestDrive(OperatorLeftYAxis, OperatorRightYAxis));

        DriverAButton.whenPressed(new ToggleIntakePosition());
        OperatorBButton.toggleWhenPressed(new TestCommand());
    }

    @Override
    public void teleOpStop() {

    }

}

