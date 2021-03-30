package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.ExtendIntake;
import org.firstinspires.ftc.teamcode.commands.actions.RetractIntake;
import org.firstinspires.ftc.teamcode.commands.actions.SetIntakePosition;
import org.firstinspires.ftc.teamcode.commands.actions.TestTelemetryCommand;

@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        scheduler.enableDebugTelemetry();
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

