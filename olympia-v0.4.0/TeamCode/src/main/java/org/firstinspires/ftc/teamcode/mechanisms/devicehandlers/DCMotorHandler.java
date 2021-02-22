package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class DCMotorHandler {
    protected boolean flipped;
    protected String deviceName;
    protected DcMotor motor;

    private double previousMotorPower = 0;

    public DCMotorHandler (String deviceName, boolean flipped) {
        this.deviceName = deviceName;
        this.flipped = flipped;

        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (flipped) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotor.class, deviceName);
    }

    public void setPower(double motorPower) {
        if (motorPower != previousMotorPower) {
            motor.setPower(Range.clip(motorPower, MOTOR_WRAPPER_LOWER_SPEED_BOUND, MOTOR_WRAPPER_UPPER_SPEED_BOUND));
            previousMotorPower = motorPower;
        }
    }
}