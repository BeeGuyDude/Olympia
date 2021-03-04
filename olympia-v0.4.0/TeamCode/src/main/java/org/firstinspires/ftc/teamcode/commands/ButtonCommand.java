package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class ButtonCommand implements Command {

    private Command wrappedCommand;
    private Button button;

    private boolean buttonState;

    public ButtonCommand(Command wrappedCommand, Button button) {
        this.wrappedCommand = wrappedCommand;
        this.button = button;
    }

    public void initialize() {

    }

    public void execute() {
        buttonState = button.get();
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }

}
