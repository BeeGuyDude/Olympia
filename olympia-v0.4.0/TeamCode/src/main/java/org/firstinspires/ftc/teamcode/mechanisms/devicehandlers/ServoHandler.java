package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoHandler {

    private String deviceName;
    private Servo servo;

    private double previousTargetPosition = 0;

    public ServoHandler(String deviceName) {
        this.deviceName = deviceName;
    }

    public void init(HardwareMap hwMap) {
        servo = hwMap.get(Servo.class, deviceName);
    }

    public void setPosition(double position) {
        if (position != previousTargetPosition) {
            servo.setPosition(position);
            previousTargetPosition = position;
        }
    }

    public double getPosition() {
        return previousTargetPosition;
        //Yes, I know that the servo has a built in method for this. It is separate.
    }

    public double getServoNativePosition() {
        return servo.getPosition();
    }
}
