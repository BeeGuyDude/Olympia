package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DCMotorHandler;

public class DriveTrain extends Mechanism {

    private DCMotorHandler leftMotor = new DCMotorHandler("left", false);
    private DCMotorHandler rightMotor = new DCMotorHandler("left", false);

    private DriveTrain() {
        registerSelf();
    }

    @Override
    void init() {}

    @Override
    public void run() {

    }
}
