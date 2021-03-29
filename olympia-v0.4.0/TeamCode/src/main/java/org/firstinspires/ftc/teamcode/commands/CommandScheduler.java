package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CommandScheduler {

    private enum CommandPriority {
        LOW,
        HIGH
    }

    private ArrayList<Command> requestedAdditionList = new ArrayList<Command>();
    private ArrayList<Command> rawCommandList = new ArrayList<Command>();
    private Map<Command, CommandPriority> commandPriority = new HashMap<Command, CommandPriority>();

    private Map<Mechanism, Command> mechanismLockingCommandMap = new HashMap<Mechanism, Command>();

    private ArrayList<Command> commandExecutionList = new ArrayList<Command>();
    private ArrayList<Boolean> commandInitializedList = new ArrayList<Boolean>();

    private ArrayList<Command> finishedAndRemovedCommands = new ArrayList<Command>();

    public void add(Command command) {
        rawCommandList.add(command);
        commandPriority.put(command, CommandPriority.LOW);
    }

    public void addUninterruptible(Command command) {
        rawCommandList.add(command);
        commandPriority.put(command, CommandPriority.HIGH);
    }

    public void requestCommandExecution(Command command) {
        if (!requestedAdditionList.contains(command)) {
            ArrayList<Command> requestedListRemovalList = new ArrayList<Command>();
            TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Not found, attempting initialization");
            for (Command cachedCommand : requestedAdditionList) {
                if (!command.getBoundMechanisms().isEmpty()) {
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (cachedCommand.getBoundMechanisms().contains(mechanism)) {
                            requestedListRemovalList.add(cachedCommand);
                            TelemetryHandler.getInstance().getTelemetry().addData(cachedCommand.toString(), "Removed");
                        }
                    }
                }
            }

            TelemetryHandler.getInstance().getTelemetry().addData("Execution", command.toString());
            requestedAdditionList.add(command);

            requestedAdditionList.removeAll(requestedListRemovalList);
            requestedListRemovalList.clear();
        }
    }

    public void requestCommandTermination(Command command) {
        if (requestedAdditionList.contains(command)) {
            TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Removed from request list");
            requestedAdditionList.remove(command);
        }

        if (commandExecutionList.contains(command)) {
            if (commandInitializedList.get(commandExecutionList.indexOf(command)) == true) {
                command.end();
            }

            TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Removed from execution list");

            commandInitializedList.remove(commandExecutionList.indexOf(command));
            finishedAndRemovedCommands.add(command);
        }
    }

    public void run() {
        //Looped Command Checking
        if (!rawCommandList.isEmpty()) {
            for (Command command : rawCommandList) {
                boolean passedCheck = true;

                if (commandExecutionList.contains(command)) {
                    passedCheck = false;
                    TelemetryHandler.getInstance().getTelemetry().addData("Command detected itself", passedCheck);
                }

                if (!command.getBoundMechanisms().isEmpty()) {
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(mechanism)) {
                            if (commandPriority.get(command) == CommandPriority.LOW && commandPriority.get(mechanismLockingCommandMap.get(mechanism)) == CommandPriority.HIGH) {
                                passedCheck = false;
                                TelemetryHandler.getInstance().getTelemetry().addData("Priority Conflict", "Detected");
                                break;
                            }
                        }
                    }
                }

                if (passedCheck) {
                    for (Mechanism boundMechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(boundMechanism) && mechanismLockingCommandMap.get(boundMechanism) != null) {
                            mechanismLockingCommandMap.get(boundMechanism).end();
                            commandInitializedList.remove(commandExecutionList.indexOf(mechanismLockingCommandMap.get(boundMechanism)));
                            commandExecutionList.remove(mechanismLockingCommandMap.get(boundMechanism));
                        }

                        mechanismLockingCommandMap.put(boundMechanism, command);
                    }

                    commandExecutionList.add(command);
                    commandInitializedList.add(false);
                }

                TelemetryHandler.getInstance().getTelemetry().addData("passedCheck", passedCheck);
            }
        }

        //Requested Command Checking
        if (!requestedAdditionList.isEmpty()) {
            ArrayList<Command> executedAndRemovedCommands = new ArrayList <Command>();
            for (Command command : requestedAdditionList) {
                boolean passedCheck = true;

                if (!command.getBoundMechanisms().isEmpty()) {
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(mechanism)) {
                            if (commandPriority.get(command) == CommandPriority.LOW && commandPriority.get(mechanismLockingCommandMap.get(mechanism)) == CommandPriority.HIGH) {
                                passedCheck = false;
                                TelemetryHandler.getInstance().getTelemetry().addData("Priority Conflict", "Detected");
                                break;
                            }
                        }
                    }
                }

                if (passedCheck) {
                    for (Mechanism boundMechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(boundMechanism) && mechanismLockingCommandMap.get(boundMechanism) != null) {
                            mechanismLockingCommandMap.get(boundMechanism).end();
                            commandInitializedList.remove(commandExecutionList.indexOf(mechanismLockingCommandMap.get(boundMechanism)));
                            commandExecutionList.remove(mechanismLockingCommandMap.get(boundMechanism));
                        }

                        mechanismLockingCommandMap.put(boundMechanism, command);
                    }

                    commandExecutionList.add(command);
                    commandInitializedList.add(false);
                    TelemetryHandler.getInstance().getTelemetry().addData("False", "Added to initialized List");


                    executedAndRemovedCommands.add(command);
                }

                TelemetryHandler.getInstance().getTelemetry().addData("passedCheck", passedCheck);
            }
            requestedAdditionList.removeAll(executedAndRemovedCommands);
            executedAndRemovedCommands.clear();
        }

        if (!commandExecutionList.isEmpty()) {
            for (Command command : commandExecutionList) {
                TelemetryHandler.getInstance().getTelemetry().addData("Executing", command.toString());
                if (commandInitializedList.get(commandExecutionList.indexOf(command)) == false) {
                    command.initialize();
                    commandInitializedList.set(commandExecutionList.indexOf(command), true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    commandInitializedList.remove(commandExecutionList.indexOf(command));
                    finishedAndRemovedCommands.add(command);
                }
            }
            commandExecutionList.removeAll(finishedAndRemovedCommands);
            finishedAndRemovedCommands.clear();
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
        for (Command command : rawCommandList) {
            telemetry.addData(command.toString(), "In Looped List");
        }
        for (Command command : requestedAdditionList) {
            telemetry.addData(command.toString(), "In Requested List");
        }
        for (Command command : commandExecutionList) {
            telemetry.addData(command.toString(), "In Execution List");
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
