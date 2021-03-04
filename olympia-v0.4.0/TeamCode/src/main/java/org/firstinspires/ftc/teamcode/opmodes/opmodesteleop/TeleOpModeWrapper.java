package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

abstract class TeleOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    public Button AButton = new Button(gamepad1, ButtonID.A_BUTTON, scheduler);
    public Button BButton = new Button(gamepad1, ButtonID.B_BUTTON, scheduler);
    public Button XButton = new Button(gamepad1, ButtonID.X_BUTTON, scheduler);
    public Button YButton = new Button(gamepad1, ButtonID.Y_BUTTON, scheduler);
    public Button LeftBumper = new Button(gamepad1, ButtonID.LEFT_BUMPER, scheduler);
    public Button RightBumper = new Button(gamepad1, ButtonID.RIGHT_BUMPER, scheduler);
    public Button DPadUp = new Button(gamepad1, ButtonID.UP, scheduler);
    public Button DPadRight = new Button(gamepad1, ButtonID.RIGHT, scheduler);
    public Button DPdDown = new Button(gamepad1, ButtonID.DOWN, scheduler);
    public Button DPadLeft = new Button(gamepad1, ButtonID.LEFT, scheduler);
    public Button BackButton = new Button(gamepad1, ButtonID.BACK, scheduler);
    public Button StartButton = new Button(gamepad1, ButtonID.START, scheduler);

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
