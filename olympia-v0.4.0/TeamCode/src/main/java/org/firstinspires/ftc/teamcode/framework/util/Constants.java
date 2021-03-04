package org.firstinspires.ftc.teamcode.framework.util;

/*
  <------------INCLUDE LINE - NONSTANDARD------------------------------------>
    import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;
  <-------------------------------------------------------------------------->
*/

public class Constants {
    //Motor Wrapper
    public static final int MOTOR_WRAPPER_UPPER_SPEED_BOUND = 1;
    public static final int MOTOR_WRAPPER_LOWER_SPEED_BOUND = -1;

    //Controller stuff
    public static final float CONTROLLER_1_DEADZONE = 0.05f;
    public static final double CONTROLLER_2_DEADZONE = 0.05f;

    public enum ButtonID {
        A_BUTTON,
        B_BUTTON,
        X_BUTTON,
        Y_BUTTON,
        LEFT_BUMPER,
        RIGHT_BUMPER,
        UP,
        RIGHT,
        DOWN,
        LEFT,
        BACK,
        START
    }

    public enum ButtonStateRule {
        WHEN_PRESSED,
        WHEN_UNPRESSED,
        WHILE_PRESSED,
        TOGGLE_WHEN_PRESSED
    }

}
