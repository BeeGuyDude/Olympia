package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

abstract class TeleOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    //Driver
    public Button DriverAButton = new Button(gamepad1, ButtonID.A_BUTTON, scheduler);
    public Button DriverBButton = new Button(gamepad1, ButtonID.B_BUTTON, scheduler);
    public Button DriverXButton = new Button(gamepad1, ButtonID.X_BUTTON, scheduler);
    public Button DriverYButton = new Button(gamepad1, ButtonID.Y_BUTTON, scheduler);
    public Button DriverLeftBumper = new Button(gamepad1, ButtonID.LEFT_BUMPER, scheduler);
    public Button DriverRightBumper = new Button(gamepad1, ButtonID.RIGHT_BUMPER, scheduler);
    public Button DriverDPadUp = new Button(gamepad1, ButtonID.UP, scheduler);
    public Button DriverDPadRight = new Button(gamepad1, ButtonID.RIGHT, scheduler);
    public Button DriverDPdDown = new Button(gamepad1, ButtonID.DOWN, scheduler);
    public Button DriverDPadLeft = new Button(gamepad1, ButtonID.LEFT, scheduler);
    public Button DriverBackButton = new Button(gamepad1, ButtonID.BACK, scheduler);
    public Button DriverStartButton = new Button(gamepad1, ButtonID.START, scheduler);
    public Button DriverCenterButton = new Button(gamepad1, ButtonID.CENTER, scheduler);

    public Axis DriverLeftXAxis = new Axis(gamepad1, AxisID.LEFT_X);
    public Axis DriverLeftYAxis = new Axis(gamepad1, AxisID.LEFT_Y);
    public Axis DriverRightXAxis = new Axis(gamepad1, AxisID.RIGHT_X);
    public Axis DriverRightYAxis = new Axis(gamepad1, AxisID.RIGHT_Y);
    public Axis DriverLeftTrigger = new Axis(gamepad1, AxisID.LEFT_TRIGGER);
    public Axis DriverRightTrigger = new Axis(gamepad1, AxisID.RIGHT_TRIGGER);

    //Operator
    public Button OperatorAButton = new Button(gamepad2, ButtonID.A_BUTTON, scheduler);
    public Button OperatorBButton = new Button(gamepad2, ButtonID.B_BUTTON, scheduler);
    public Button OperatorXButton = new Button(gamepad2, ButtonID.X_BUTTON, scheduler);
    public Button OperatorYButton = new Button(gamepad2, ButtonID.Y_BUTTON, scheduler);
    public Button OperatorLeftBumper = new Button(gamepad2, ButtonID.LEFT_BUMPER, scheduler);
    public Button OperatorRightBumper = new Button(gamepad2, ButtonID.RIGHT_BUMPER, scheduler);
    public Button OperatorDPadUp = new Button(gamepad2, ButtonID.UP, scheduler);
    public Button OperatorDPadRight = new Button(gamepad2, ButtonID.RIGHT, scheduler);
    public Button OperatorDPdDown = new Button(gamepad2, ButtonID.DOWN, scheduler);
    public Button OperatorDPadLeft = new Button(gamepad2, ButtonID.LEFT, scheduler);
    public Button OperatorBackButton = new Button(gamepad2, ButtonID.BACK, scheduler);
    public Button OperatorStartButton = new Button(gamepad2, ButtonID.START, scheduler);
    public Button OperatorCenterButton = new Button(gamepad2, ButtonID.CENTER, scheduler);

    public Axis OperatorLeftXAxis = new Axis(gamepad2, AxisID.LEFT_X);
    public Axis OperatorLeftYAxis = new Axis(gamepad2, AxisID.LEFT_Y);
    public Axis OperatorRightXAxis = new Axis(gamepad2, AxisID.RIGHT_X);
    public Axis OperatorRightYAxis = new Axis(gamepad2, AxisID.RIGHT_Y);
    public Axis OperatorLeftTrigger = new Axis(gamepad2, AxisID.LEFT_TRIGGER);
    public Axis OperatorRightTrigger = new Axis(gamepad2, AxisID.RIGHT_TRIGGER);

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(CONTROLLER_1_DEADZONE);
        gamepad2.setJoystickDeadzone(CONTROLLER_2_DEADZONE);

        teleOpInit();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        teleOpLoop();
    }
    public abstract void teleOpInit();

    @Override
    public void loop() {
        scheduler.run();
    }
    public abstract void teleOpLoop();

    @Override
    public void stop() {
        teleOpStop();

        scheduler.end();
    }
    public abstract void teleOpStop();
}
