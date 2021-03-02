package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.Command;
import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class Button {

    private Gamepad gamepad;
    private ButtonID buttonId;

    private Command localCommand;

    public Button(Gamepad gamepad, ButtonID buttonId) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
    }

    private boolean get() {
        switch (buttonId) {
            case A_BUTTON:
                return gamepad.a;
            case B_BUTTON:
                return gamepad.b;
            case X_BUTTON:
                return gamepad.x;
            case Y_BUTTON:
                return gamepad.y;
            case LEFT_BUMPER:
                return gamepad.left_bumper;
            case RIGHT_BUMPER:
                return gamepad.right_bumper;
            case UP:
                return gamepad.dpad_up;
            case RIGHT:
                return gamepad.dpad_right;
            case DOWN:
                return gamepad.dpad_down;
            case LEFT:
                return gamepad.dpad_left;
        }
        return false;
    }
}
