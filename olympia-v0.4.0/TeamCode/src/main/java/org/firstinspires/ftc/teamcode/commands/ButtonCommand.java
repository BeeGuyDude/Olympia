package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class ButtonCommand implements Command {

    private Command wrappedCommand;
    private Button button;
    private ButtonStateRule rule;

    private boolean buttonPressed = false;
    private boolean buttonPressedPreviously = false;

    private boolean initialized = false;
    private boolean ended = false;

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

        switch (rule) {
            case WHEN_PRESSED:
                //Tomorrow Cian Problem
                break;
            case WHEN_UNPRESSED:
                //Tomorrow Cian Problem
                break;
            case WHILE_PRESSED:
                if (buttonPressed) {
                    ended = false;

                    if (!initialized) {
                        wrappedCommand.initialize();
                        initialized = true;
                    }

                    wrappedCommand.execute();
                } else if (!ended) {
                    wrappedCommand.end();
                    ended = true;
                }
                //also a Tomorrow Cian Problem
                break;
            case TOGGLE_WHEN_PRESSED:
                //Take a guess
                break;
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
