package org.firstinspires.ftc.teamcode.commands.basecommands;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

public class ButtonCommand extends Command {

    private Command wrappedCommand;
    private Button button;
    private ButtonStateRule rule;
    private CommandScheduler scheduler;

    private boolean buttonPressed = false;
    private boolean buttonPressedPreviously = false;

    private boolean running = false;

    private enum ButtonStateChange {
        NO_CHANGE,
        PRESSED,
        RELEASED
    }
    private ButtonStateChange buttonStateChange = ButtonStateChange.NO_CHANGE;

    public ButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand, CommandScheduler scheduler) {
        this.button = button;
        this.rule = rule;
        this.wrappedCommand = wrappedCommand;
        this.scheduler = scheduler;
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
                        scheduler.requestCommandTermination(wrappedCommand);
                    }
                    scheduler.requestCommandExecution(wrappedCommand);
                    running = true;
                }
                break;

            case WHEN_RELEASED:
                if (buttonStateChange == ButtonStateChange.RELEASED) {
                    if (running) {
                        scheduler.requestCommandTermination(wrappedCommand);
                    }
                    scheduler.requestCommandExecution(wrappedCommand);
                    running = true;
                }
                break;

            case WHILE_HELD:
                if (buttonStateChange == ButtonStateChange.PRESSED) {
                    scheduler.requestCommandExecution(wrappedCommand);
                    running = true;
                } else if (running && buttonStateChange == ButtonStateChange.RELEASED) {
                    scheduler.requestCommandTermination(wrappedCommand);
                    running = false;
                }
                break;

            case TOGGLE_WHEN_PRESSED:
                if (buttonStateChange == ButtonStateChange.PRESSED) {
                    if (running == true) {
                        scheduler.requestCommandTermination(wrappedCommand);
                        running = false;
                    } else {
                        scheduler.requestCommandExecution(wrappedCommand);
                        running = true;
                    }
                }
                break;
        }

        if (wrappedCommand.isFinished()) {
            running = false;
        }

        buttonPressedPreviously = buttonPressed;
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }

}
