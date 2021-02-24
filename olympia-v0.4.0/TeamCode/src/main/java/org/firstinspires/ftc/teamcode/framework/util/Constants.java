package org.firstinspires.ftc.teamcode.framework.util;

public class Constants {
    //Motor Wrapper
    public static final int MOTOR_WRAPPER_UPPER_SPEED_BOUND = 1;
    public static final int MOTOR_WRAPPER_LOWER_SPEED_BOUND = -1;

    //Controller stuff
    public static final double CONTROLLER_1_DEADZONE = 0.05;
    public static final double CONTROLLER_2_DEADZONE = 0.05;

    public static final String A_BUTTON = "what";
    public static final String B_BUTTON = "are";
    public static final String X_BUTTON = "you";
    public static final String Y_BUTTON = "doing";
    public static final String LEFT_BUMPER = "in my";
    public static final String RIGHT_BUMPER = "swamp";

    public static final String PARALLEL = "parallel";
    public static final String SEQUENTIAL = "sequential";

    public enum OpModeType {
        TELEOP,
        AUTO
    }
}
