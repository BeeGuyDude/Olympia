package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

public class DeadzonedController {
    private Gamepad gamepad;
    private double deadzone;

    public DeadzonedController(Gamepad gamepad, double deadzone) {
        this.gamepad = gamepad;
        this.deadzone = deadzone;
    }

    private double GetHypotenuse(double axis1, double axis2) {
        return (Math.sqrt(Math.pow(axis1, 2) + Math.pow(axis2, 2)));
    }

    public double getLeftXAxis() {
        if (GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y) <= deadzone) {
            return 0;
        } else {
            return ((GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y) - deadzone) / (1 - deadzone))
                    * (gamepad.left_stick_x / GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y));
        }
    }

    public double getLeftYAxis() {
        if (GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y) <= deadzone) {
            return 0;
        } else {
            return ((GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y) - deadzone) / (1 - deadzone))
                    * (gamepad.left_stick_y / GetHypotenuse(gamepad.left_stick_x, gamepad.left_stick_y));
        }
    }

    public double getRightXAxis() {
        if (GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y) <= deadzone) {
            return 0;
        } else {
            return ((GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y) - deadzone) / (1 - deadzone))
                    * (gamepad.right_stick_x / GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y));
        }
    }

    public double getRightYAxis() {
        if (GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y) <= deadzone) {
            return 0;
        } else {
            return ((GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y) - deadzone) / (1 - deadzone))
                    * (gamepad.right_stick_y / GetHypotenuse(gamepad.right_stick_x, gamepad.right_stick_y));
        }
    }
}