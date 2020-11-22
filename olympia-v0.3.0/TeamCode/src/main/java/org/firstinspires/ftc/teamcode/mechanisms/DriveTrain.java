package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorHandler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DeadzonedController;

public class DriveTrain extends Mechanism {

    private DCMotorHandler leftMotor;
    private DCMotorHandler rightMotor;

    private DeadzonedController controller = new DeadzonedController(1, 0.05);

    private DriveTrain() {
        registerSelf();
    }

    @Override
    void init() {
        leftMotor = new DCMotorHandler("left", false);
        rightMotor = new DCMotorHandler("right", true);
    }

    @Override
    public void run() {
        runDriveTrain(controller.getLeftYAxis(), controller.getRightYAxis());
    }

    public void runDriveTrain(double lSpeed, double rSpeed) {
        leftMotor.setPower(lSpeed);
        rightMotor.setPower(rSpeed);
    }
}
