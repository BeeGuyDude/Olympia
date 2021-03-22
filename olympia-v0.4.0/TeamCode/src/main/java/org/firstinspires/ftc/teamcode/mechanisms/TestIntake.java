package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.ServoHandler;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class TestIntake extends Mechanism {

    private boolean extended = false;
    private ServoHandler myServo = new ServoHandler("servo");

    @Override
    public void init(HardwareMap hwmap) {
        myServo.init(hwmap);
    }

    public void extend() {
        myServo.setPosition(INTAKE_EXTENDED_POSITION);
        extended = true;
    }

    public void retract() {
        myServo.setPosition(INTAKE_RETRACTED_POSITION);
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
