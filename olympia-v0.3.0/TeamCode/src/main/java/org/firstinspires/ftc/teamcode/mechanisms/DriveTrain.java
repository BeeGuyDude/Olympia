package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorHandler;

public class DriveTrain extends Mechanism {

    private DCMotorHandler leftMotor;
    private DCMotorHandler rightMotor;

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

    }
}
