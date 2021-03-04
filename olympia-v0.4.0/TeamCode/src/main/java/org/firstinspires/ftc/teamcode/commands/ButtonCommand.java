package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class ButtonCommand implements Command {

    private Command wrappedCommand;
    private Button button;
    private ButtonStateRule rule;

    private boolean buttonState = false;
    private boolean previousButtonState = false;

    private boolean initialized = false;

    public ButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand) {
        this.button = button;
        this.rule = rule;
        this.wrappedCommand = wrappedCommand;
    }

    public void initialize() {
        if (!initialized) {
            wrappedCommand.initialize();
            initialized = true;
        }
    }

    public void execute() {
        buttonState = button.get();

        switch (rule) {
            case WHEN_PRESSED:

                break;
            case WHEN_UNPRESSED:

                break;
            case WHILE_PRESSED:

                break;
            case TOGGLE_WHEN_PRESSED:

                break;
        }
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {
        wrappedCommand.end();
    }

}
