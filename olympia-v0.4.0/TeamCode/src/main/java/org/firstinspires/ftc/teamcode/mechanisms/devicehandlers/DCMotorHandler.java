package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.teamcode.framework.util.FrameworkConstants.*;

public class DCMotorHandler {
    private boolean flipped;
    private boolean brakeMode;
    private String deviceName;

    private DcMotor motor;

    private double previousMotorPower = 0;
    private double direction = 1;

    public DCMotorHandler(String deviceName, boolean flipped, boolean brakeMode) {
        this.deviceName = deviceName;
        this.flipped = flipped;
        this.brakeMode = brakeMode;
    }

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, deviceName);

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (flipped) direction = -1;

        if (brakeMode) {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } else {
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        }
    }

    public void setPower(double motorPower) {
        if (motorPower != previousMotorPower) {
            motor.setPower(direction * Range.clip(motorPower, MOTOR_WRAPPER_LOWER_SPEED_BOUND, MOTOR_WRAPPER_UPPER_SPEED_BOUND));
            previousMotorPower = motorPower;
        }
    }
}