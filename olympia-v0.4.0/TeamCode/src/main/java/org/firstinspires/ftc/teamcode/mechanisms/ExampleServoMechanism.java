package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.ServoHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

import static org.firstinspires.ftc.teamcode.framework.Constants.*;

public class ExampleServoMechanism extends Mechanism {

    private boolean extended = false;
    private ServoHandler myServo = new ServoHandler("servo");

    @Override
    public void init(HardwareMap hwmap) {
        myServo.init(hwmap);
    }

    public void extend() {
        myServo.setPosition(EXAMPLE_SERVO_EXTENDED_POSITION);
        extended = true;
    }

    public void retract() {
        myServo.setPosition(EXAMPLE_SERVO_RETRACTED_POSITION);
        extended = false;
    }

    public void toggle() {
        if (extended) {
            retract();
        } else {
            extend();
        }
    }

    public void setPosition(double position) {
        myServo.setPosition(position);
    }

    public double getExtension() {
        return myServo.getPosition();
    }

}
