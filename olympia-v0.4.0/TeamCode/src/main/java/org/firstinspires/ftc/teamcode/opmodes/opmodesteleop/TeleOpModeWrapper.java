package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

abstract class TeleOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(CONTROLLER_1_DEADZONE);

        teleOpInit();
    }
    public abstract void teleOpInit();

    @Override
    public void loop() {


        teleOpLoop();
    }
    public abstract void teleOpLoop();

    @Override
    public void stop() {


        teleOpStop();
    }
    public abstract void teleOpStop();
}
