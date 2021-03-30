package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.ServoHandler;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

import static org.firstinspires.ftc.teamcode.framework.Constants.*;

//Every Mechanism needs to extend Mechanism so that the MechanismEngine can see it.
public class ExampleServoMechanism extends Mechanism {

    private boolean extended = false;
    //Note that we don't use a servo, we use a ServoHandler, something from Olympia; it's a wrapper class that prevents updating devices when it isn't necessary to, cutting down cycle times.
    //Use ServoHandlers for servos, and DCMotorHandlers or DCMotorWithEncoderHandlers for DCMotors.
    private ServoHandler myServo = new ServoHandler("servo");

    //This method is inherited from Mechanism, it's very important that you give it the HardwareMap passthrough.
    //Additionally, note that the device handlers do their own setup; you just have to tell them to .init(hwmap) and they'll handle the rest.
    @Override
    public void init(HardwareMap hwmap) {
        myServo.init(hwmap);
    }

    //Add every method for what you want the mechanism to be able to do; note that **ALL MECHANISM STATES SHOULD BE STORED IN THE MECHANISM AND NOT COMMANDS.**
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
