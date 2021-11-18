package org.firstinspires.ftc.teamcode.framework.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import static org.firstinspires.ftc.teamcode.framework.util.FrameworkConstants.*;

public class Axis {
    private Gamepad gamepad;
    private AxisID axisId;

    public Axis(Gamepad gamepad, AxisID axisId) {
        this.gamepad = gamepad;
        this.axisId = axisId;
    }

    public double get() {
        double result;

        switch (axisId) {
            case LEFT_X:
                result = gamepad.left_stick_x;
                break;
            case LEFT_Y:
                result = -gamepad.left_stick_y;
                break;
            case RIGHT_X:
                result = gamepad.right_stick_x;
                break;
            case RIGHT_Y:
                result = -gamepad.right_stick_y;
                break;
            case LEFT_TRIGGER:
                result = gamepad.left_trigger;
                break;
            case RIGHT_TRIGGER:
                result = gamepad.right_trigger;
                break;

            default:
                result = 0;
        }

        return result;
    }
}
