package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class DCMotorExHandler {
    private boolean flipped;
    private String deviceName;
    private DcMotorEx motor;

    private double previousMotorPower = 0;

    public DCMotorExHandler (String deviceName, boolean flipped) {
        this.deviceName = deviceName;
        this.flipped = flipped;

        motor.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        if (flipped) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    public void init(HardwareMap hwMap) {
        motor = hwMap.get(DcMotorEx.class, deviceName);
    }

    public void setPower(double motorPower) {
        if (motorPower != previousMotorPower) {
            motor.setPower(Range.clip(motorPower, MOTOR_WRAPPER_LOWER_SPEED_BOUND, MOTOR_WRAPPER_UPPER_SPEED_BOUND));
            previousMotorPower = motorPower;
        }
    }


}