package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;

public class DeadzonedStick {
    private OpMode currentOpMode = FTCRuntimeHandler.getOpMode();
    private int controllerNum;
    private double deadzone;

    DeadzonedStick(int controllerNum, double deadzone) {
        this.controllerNum = controllerNum;
        this.deadzone = deadzone;
    }

    private double GetHypotenuse(double axis1, double axis2) {
        return (Math.sqrt(Math.pow(axis1, 2) + Math.pow(axis2, 2)));
    }

    public double GetLeftXAxis() {
        switch (controllerNum) {
            case 1:
                if (GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y) - deadzone) / (1 - deadzone))
                    * (currentOpMode.gamepad1.left_stick_x / GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y));
                }
            case 2:
                if (GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad2.left_stick_x / GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y));
                }
        }
        return 0;
    }
    
    public double GetLeftYAxis() {
        switch (controllerNum) {
            case 1:
                if (GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad1.left_stick_y / GetHypotenuse(currentOpMode.gamepad1.left_stick_x, currentOpMode.gamepad1.left_stick_y));
                }
            case 2:
                if (GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad2.left_stick_y / GetHypotenuse(currentOpMode.gamepad2.left_stick_x, currentOpMode.gamepad2.left_stick_y));
                }
        }
        return 0;
    }

    public double GetRightXAxis() {
        switch (controllerNum) {
            case 1:
                if (GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad1.right_stick_x / GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y));
                }
            case 2:
                if (GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad2.right_stick_x / GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y));
                }
        }
        return 0;
    }

    public double GetRightYAxis() {
        switch (controllerNum) {
            case 1:
                if (GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad1.right_stick_y / GetHypotenuse(currentOpMode.gamepad1.right_stick_x, currentOpMode.gamepad1.right_stick_y));
                }
            case 2:
                if (GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y) <= deadzone) {
                    return 0;
                } else {
                    return ((GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y) - deadzone) / (1 - deadzone))
                            * (currentOpMode.gamepad2.right_stick_y / GetHypotenuse(currentOpMode.gamepad2.right_stick_x, currentOpMode.gamepad2.right_stick_y));
                }
        }
        return 0;
    }
}
