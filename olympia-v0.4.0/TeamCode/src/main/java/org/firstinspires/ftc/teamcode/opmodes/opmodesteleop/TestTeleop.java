package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;

@TeleOp
public class TestTeleop extends TeleOpModeWrapper {

    @Override
    public void teleOpInit() {
        telemetry.addData("Test", "Success");

    }

    @Override
    public void teleOpLoop() {
        scheduler.addSequential(new TestCommand());
    }

    @Override
    public void teleOpStop() {

    }

}

