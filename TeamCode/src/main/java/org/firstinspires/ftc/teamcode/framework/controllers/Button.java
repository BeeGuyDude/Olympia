package org.firstinspires.ftc.teamcode.framework.controllers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.commands.CommandScheduler;

import static org.firstinspires.ftc.teamcode.framework.util.FrameworkConstants.*;

public class Button {

    private Gamepad gamepad;
    private ButtonID buttonId;
    private CommandScheduler scheduler;

    public Button(Gamepad gamepad, ButtonID buttonId, CommandScheduler scheduler) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
        this.scheduler = scheduler;
    }

    public boolean get() {
        boolean result;

        switch (buttonId) {
            case A_BUTTON:
                result = gamepad.a;
                break;
            case B_BUTTON:
                result = gamepad.b;
                break;
            case X_BUTTON:
                result = gamepad.x;
                break;
            case Y_BUTTON:
                result = gamepad.y;
                break;
            case LEFT_BUMPER:
                result = gamepad.left_bumper;
                break;
            case RIGHT_BUMPER:
                result = gamepad.right_bumper;
                break;
            case UP:
                result = gamepad.dpad_up;
                break;
            case RIGHT:
                result = gamepad.dpad_right;
                break;
            case DOWN:
                result = gamepad.dpad_down;
                break;
            case LEFT:
                result = gamepad.dpad_left;
                break;
            case BACK:
                result = gamepad.back;
                break;
            case START:
                result = gamepad.start;
                break;
            case CENTER:
                result = gamepad.guide;
                break;

            default:
                result = false;
        }
        return result;
    }

    public void whenPressed(Command command) {
        scheduler.addButtonCommand(this, ButtonStateRule.WHEN_PRESSED, command);
    }

    public void whenReleased(Command command) {
        scheduler.addButtonCommand(this, ButtonStateRule.WHEN_RELEASED, command);
    }

    public void whileHeld(Command command) {
        scheduler.addButtonCommand(this, ButtonStateRule.WHILE_HELD, command);
    }

    public void toggleWhenPressed(Command command) {
        scheduler.addButtonCommand(this, ButtonStateRule.TOGGLE_WHEN_PRESSED, command);
    }
}
