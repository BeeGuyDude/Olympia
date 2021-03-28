package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.framework.util.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class CommandScheduler {

    private enum commandPriority {
        LOW,
        HIGH
    }

    private ArrayList<Command> requestedAdditionList = new ArrayList<Command>();
    private ArrayList<Command> rawCommandList = new ArrayList<Command>();
    private Map<Command, commandPriority> boundCommandPriority = new HashMap<Command, commandPriority>();

    private Map<Mechanism, ArrayList<Command>> mechanismBindingMap = new HashMap<Mechanism, ArrayList<Command>>();
    private Map<Mechanism, Command> mechanismLockingCommandMap = new HashMap<Mechanism, Command>();

    private ArrayList<Command> commandExecutionList = new ArrayList<Command>();
    private ArrayList<Boolean> commandInitializedList = new ArrayList<Boolean>();

    public void add(Command command) {
        rawCommandList.add(command);

        if (!command.getBoundMechanisms().isEmpty()) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                if (mechanismBindingMap.containsKey(mechanism)) {
                    mechanismBindingMap.get(mechanism).add(command);
                } else {
                    mechanismBindingMap.put(mechanism, new ArrayList(Arrays.asList(command)));
                }

                boundCommandPriority.put(command, commandPriority.LOW);
            }
        }
    }

    public void addHighPriority(Command command) {
        rawCommandList.add(command);
        commandInitializedList.add(false);

        if (!command.getBoundMechanisms().isEmpty()) {
            if (!command.getBoundMechanisms().isEmpty()) {
                for (Mechanism mechanism : command.getBoundMechanisms()) {
                    if (mechanismBindingMap.containsKey(mechanism)) {
                        mechanismBindingMap.get(mechanism).add(command);
                    } else {
                        mechanismBindingMap.put(mechanism, new ArrayList(Arrays.asList(command)));
                    }
                }
                boundCommandPriority.put(command, commandPriority.HIGH);
            }
        }
    }

    public void requestCommandExecution(Command command) {
        if (!requestedAdditionList.contains(command)) {
            requestedAdditionList.add(command);
        }

        for (Command cachedCommand : requestedAdditionList) {
            if (!command.getBoundMechanisms().isEmpty()) {
                for (Mechanism mechanism : command.getBoundMechanisms()) {
                    if (cachedCommand.getBoundMechanisms().contains(mechanism)) {
                        requestedAdditionList.remove(cachedCommand);
                    }
                }
            }
        }
    }

    public void requestCommandTermination(Command command) {
        if (requestedAdditionList.contains(command)) {
            requestedAdditionList.remove(command);
        }

        if (commandExecutionList.contains(command)) {
            if (commandInitializedList.get(commandExecutionList.indexOf(command)) == true) command.end();

            commandInitializedList.remove(commandExecutionList.indexOf(command));
            commandExecutionList.remove(command);
        }
    }

    public void run() {
        if (!rawCommandList.isEmpty()) {
            for (Command command : rawCommandList) {
                boolean passedCheck = true;

                if (commandExecutionList.contains(command)) passedCheck = false;

                if (!command.getBoundMechanisms().isEmpty()) {
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(mechanism)) {
                            if (boundCommandPriority.get(command) == commandPriority.LOW && boundCommandPriority.get(mechanismLockingCommandMap.get(mechanism)) == commandPriority.HIGH) {
                                passedCheck = false;
                                break;
                            }
                        }
                    }
                }

                if (passedCheck) {
                    for (Mechanism boundMechanism : command.getBoundMechanisms()) {
                        mechanismLockingCommandMap.get(boundMechanism).end();
                        commandInitializedList.remove(commandExecutionList.indexOf(mechanismLockingCommandMap.get(boundMechanism)));
                        commandExecutionList.remove(mechanismLockingCommandMap.get(boundMechanism));
                    }

                    commandExecutionList.add(command);
                    commandInitializedList.add(false);
                }
            }
        }

        if (!commandExecutionList.isEmpty()) {
            for (Command command : commandExecutionList) {

                if (commandInitializedList.get(commandExecutionList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitializedList.set(commandExecutionList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandInitializedList.remove(commandExecutionList.indexOf(command));
                    commandExecutionList.remove(command);
                }
            }
        }
    }

    public boolean isRunning(Command command) {
        return commandExecutionList.contains(command);
    }

    public void end() {
        for (Command command : rawCommandList) {
            command.end();
        }
    }

    public boolean isEmpty() {
        return commandExecutionList.isEmpty();
    }

    public void postCommands(Telemetry telemetry) {
        for (Command command : commandExecutionList) {
            telemetry.addData(command.toString(), "Initialized");
        }
    }

    public void addButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand) {
        commandExecutionList.add(new ButtonCommand(button, rule, wrappedCommand));
        commandInitializedList.add(false);
    }

    private class ButtonCommand extends Command {

        private Command wrappedCommand;
        private Button button;
        private ButtonStateRule rule;

        private boolean buttonPressed = false;
        private boolean buttonPressedPreviously = false;

        private boolean running = false;

        private ButtonStateChange buttonStateChange = ButtonStateChange.NO_CHANGE;

        public ButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand) {
            this.button = button;
            this.rule = rule;
            this.wrappedCommand = wrappedCommand;
        }

        public void initialize() {}

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
                            requestCommandTermination(wrappedCommand);
                        }
                        requestCommandExecution(wrappedCommand);
                        running = true;
                    }
                    break;

                case WHEN_RELEASED:
                    if (buttonStateChange == ButtonStateChange.RELEASED) {
                        if (running) {
                            requestCommandTermination(wrappedCommand);
                        }
                        requestCommandExecution(wrappedCommand);
                        running = true;
                    }
                    break;

                case WHILE_HELD:
                    if (buttonStateChange == ButtonStateChange.PRESSED) {
                        requestCommandExecution(wrappedCommand);
                        running = true;
                    } else if (running && buttonStateChange == ButtonStateChange.RELEASED) {
                        requestCommandTermination(wrappedCommand);
                        running = false;
                    }
                    break;

                case TOGGLE_WHEN_PRESSED:
                    if (running) {
                        if (!isRunning(wrappedCommand)) running = false;
                    }

                    if (buttonStateChange == ButtonStateChange.PRESSED) {
                        if (running) {
                            requestCommandTermination(wrappedCommand);
                            running = false;
                        } else {
                            requestCommandExecution(wrappedCommand);
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

        public void end() {}
    }
}
