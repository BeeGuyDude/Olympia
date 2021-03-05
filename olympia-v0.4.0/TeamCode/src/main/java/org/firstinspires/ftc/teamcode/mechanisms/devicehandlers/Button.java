package org.firstinspires.ftc.teamcode.mechanisms.devicehandlers;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.Command;
import org.firstinspires.ftc.teamcode.commands.ButtonCommand;
import org.firstinspires.ftc.teamcode.commands.CommandScheduler;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class Button {

    private Gamepad gamepad;
    private ButtonID buttonId;
    private CommandScheduler scheduler;

    private Command localCommand;

    public Button(Gamepad gamepad, ButtonID buttonId, CommandScheduler scheduler) {
        this.gamepad = gamepad;
        this.buttonId = buttonId;
        this.scheduler = scheduler;
    }

    public boolean get() {
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
            case BACK:
                return gamepad.back;
            case START:
                return gamepad.start;
            case CENTER:
                return gamepad.guide;
        }
        return false;
    }

    public void whenPressed(Command command) {
        scheduler.addLooped(new ButtonCommand(this, ButtonStateRule.WHEN_PRESSED, command));
    }

    public void whenReleased(Command command) {
        scheduler.addLooped(new ButtonCommand(this, ButtonStateRule.WHEN_RELEASED, command));
    }

    public void whileHeld(Command command) {
        scheduler.addLooped(new ButtonCommand(this, ButtonStateRule.WHILE_HELD, command));
    }

    public void toggleWhenPressed(Command command) {
        scheduler.addLooped(new ButtonCommand(this, ButtonStateRule.TOGGLE_WHEN_PRESSED, command));
    }
}
