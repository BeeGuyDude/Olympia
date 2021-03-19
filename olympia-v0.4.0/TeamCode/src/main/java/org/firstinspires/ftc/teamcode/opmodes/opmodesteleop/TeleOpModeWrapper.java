package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

abstract class TeleOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    //Driver
    public Button DriverAButton;
    public Button DriverBButton;
    public Button DriverXButton;
    public Button DriverYButton;
    public Button DriverLeftBumper;
    public Button DriverRightBumper;
    public Button DriverDPadUp;
    public Button DriverDPadRight;
    public Button DriverDPdDown;
    public Button DriverDPadLeft;
    public Button DriverBackButton;
    public Button DriverStartButton;
    public Button DriverCenterButton;

    public Axis DriverLeftXAxis;
    public Axis DriverLeftYAxis;
    public Axis DriverRightXAxis;
    public Axis DriverRightYAxis;
    public Axis DriverLeftTrigger;
    public Axis DriverRightTrigger;

    //Operator
    public Button OperatorAButton;
    public Button OperatorBButton;
    public Button OperatorXButton;
    public Button OperatorYButton;
    public Button OperatorLeftBumper;
    public Button OperatorRightBumper;
    public Button OperatorDPadUp;
    public Button OperatorDPadRight;
    public Button OperatorDPdDown;
    public Button OperatorDPadLeft;
    public Button OperatorBackButton;
    public Button OperatorStartButton;
    public Button OperatorCenterButton;

    public Axis OperatorLeftXAxis;
    public Axis OperatorLeftYAxis;
    public Axis OperatorRightXAxis;
    public Axis OperatorRightYAxis;
    public Axis OperatorLeftTrigger;
    public Axis OperatorRightTrigger;

    @Override
    public void init() {
        DriverAButton = new Button(gamepad1, ButtonID.A_BUTTON, scheduler);
        DriverBButton = new Button(gamepad1, ButtonID.B_BUTTON, scheduler);
        DriverXButton = new Button(gamepad1, ButtonID.X_BUTTON, scheduler);
        DriverYButton = new Button(gamepad1, ButtonID.Y_BUTTON, scheduler);
        DriverLeftBumper = new Button(gamepad1, ButtonID.LEFT_BUMPER, scheduler);
        DriverRightBumper = new Button(gamepad1, ButtonID.RIGHT_BUMPER, scheduler);
        DriverDPadUp = new Button(gamepad1, ButtonID.UP, scheduler);
        DriverDPadRight = new Button(gamepad1, ButtonID.RIGHT, scheduler);
        DriverDPdDown = new Button(gamepad1, ButtonID.DOWN, scheduler);
        DriverDPadLeft = new Button(gamepad1, ButtonID.LEFT, scheduler);
        DriverBackButton = new Button(gamepad1, ButtonID.BACK, scheduler);
        DriverStartButton = new Button(gamepad1, ButtonID.START, scheduler);
        DriverCenterButton = new Button(gamepad1, ButtonID.CENTER, scheduler);

        DriverLeftXAxis = new Axis(gamepad1, AxisID.LEFT_X);
        DriverLeftYAxis = new Axis(gamepad1, AxisID.LEFT_Y);
        DriverRightXAxis = new Axis(gamepad1, AxisID.RIGHT_X);
        DriverRightYAxis = new Axis(gamepad1, AxisID.RIGHT_Y);
        DriverLeftTrigger = new Axis(gamepad1, AxisID.LEFT_TRIGGER);
        DriverRightTrigger = new Axis(gamepad1, AxisID.RIGHT_TRIGGER);

        //Operator
        OperatorAButton = new Button(gamepad2, ButtonID.A_BUTTON, scheduler);
        OperatorBButton = new Button(gamepad2, ButtonID.B_BUTTON, scheduler);
        OperatorXButton = new Button(gamepad2, ButtonID.X_BUTTON, scheduler);
        OperatorYButton = new Button(gamepad2, ButtonID.Y_BUTTON, scheduler);
        OperatorLeftBumper = new Button(gamepad2, ButtonID.LEFT_BUMPER, scheduler);
        OperatorRightBumper = new Button(gamepad2, ButtonID.RIGHT_BUMPER, scheduler);
        OperatorDPadUp = new Button(gamepad2, ButtonID.UP, scheduler);
        OperatorDPadRight = new Button(gamepad2, ButtonID.RIGHT, scheduler);
        OperatorDPdDown = new Button(gamepad2, ButtonID.DOWN, scheduler);
        OperatorDPadLeft = new Button(gamepad2, ButtonID.LEFT, scheduler);
        OperatorBackButton = new Button(gamepad2, ButtonID.BACK, scheduler);
        OperatorStartButton = new Button(gamepad2, ButtonID.START, scheduler);
        OperatorCenterButton = new Button(gamepad2, ButtonID.CENTER, scheduler);

        OperatorLeftXAxis = new Axis(gamepad2, AxisID.LEFT_X);
        OperatorLeftYAxis = new Axis(gamepad2, AxisID.LEFT_Y);
        OperatorRightXAxis = new Axis(gamepad2, AxisID.RIGHT_X);
        OperatorRightYAxis = new Axis(gamepad2, AxisID.RIGHT_Y);
        OperatorLeftTrigger = new Axis(gamepad2, AxisID.LEFT_TRIGGER);
        OperatorRightTrigger = new Axis(gamepad2, AxisID.RIGHT_TRIGGER);

        gamepad1.setJoystickDeadzone(CONTROLLER_1_DEADZONE);
        gamepad2.setJoystickDeadzone(CONTROLLER_2_DEADZONE);

        teleOpInit();
        MechanismEngine.getInstance().setHardwareMap(hardwareMap);
        MechanismEngine.getInstance().initializeMechanisms();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        teleOpLoop();
        MechanismEngine.getInstance().initializeMechanisms();
        //yes I know it does it twice, you don't know if some mechanisms aren't used yet until the loop portion
    }
    public abstract void teleOpInit();

    @Override
    public void loop() {
        telemetry.addData("Right Trigger Value", gamepad1.right_stick_x);
        telemetry.addData("A Button Value", gamepad1.a);
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
