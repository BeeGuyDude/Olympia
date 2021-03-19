package org.firstinspires.ftc.teamcode.commands.basecommands;

import org.firstinspires.ftc.teamcode.framework.controllers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class ButtonCommand implements Command {

    private Command wrappedCommand;
    private Button button;
    private ButtonStateRule rule;

    private boolean buttonPressed = false;
    private boolean buttonPressedPreviously = false;

    private boolean running = false;

    private enum ButtonStateChange {
        NO_CHANGE,
        PRESSED,
        RELEASED
    }
    private ButtonStateChange buttonStateChange = ButtonStateChange.NO_CHANGE;

    public ButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand) {
        this.button = button;
        this.rule = rule;
        this.wrappedCommand = wrappedCommand;
    }

    public void initialize() {
        //I'ma just leave this empty for now and see if there's something I can do with it to simplify the fustercluck that's about to come out of the execute block
    }

    public void execute() {
        buttonPressed = button.get();

        if (buttonPressed && !buttonPressedPreviously) {
            buttonStateChange = ButtonStateChange.PRESSED;
        } else if (!buttonPressed && buttonPressedPreviously) {
            buttonStateChange = ButtonStateChange.RELEASED;
        } else {
            buttonStateChange = ButtonStateChange.NO_CHANGE;
        }

        switch (rule) {
            case WHEN_PRESSED:
                if (buttonStateChange == ButtonStateChange.PRESSED) {
                    if (running) {
                        wrappedCommand.end();
                    }
                    wrappedCommand.initialize();
                    running = true;
                }
                break;

            case WHEN_RELEASED:
                if (buttonStateChange == ButtonStateChange.RELEASED) {
                    if (running) {
                        wrappedCommand.end();
                    }
                    wrappedCommand.initialize();
                    running = true;
                }
                break;

            case WHILE_HELD:
                if (buttonStateChange == ButtonStateChange.PRESSED) {
                    wrappedCommand.initialize();
                    running = true;
                } else if (running && buttonStateChange == ButtonStateChange.RELEASED) {
                    wrappedCommand.end();
                    running = false;
                }
                break;

            case TOGGLE_WHEN_PRESSED:
                if (buttonStateChange == ButtonStateChange.PRESSED) {
                    if (running == true) {
                        wrappedCommand.end();
                        running = false;
                    } else {
                        wrappedCommand.initialize();
                        running = true;
                    }
                }
                break;
        }

        if (running) {
            wrappedCommand.execute();
        }

        buttonPressedPreviously = buttonPressed;
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        wrappedCommand.end();
    }

}
