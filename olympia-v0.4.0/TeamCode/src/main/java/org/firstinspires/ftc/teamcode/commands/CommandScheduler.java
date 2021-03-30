package org.firstinspires.ftc.teamcode.commands;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class CommandScheduler {

    private enum CommandPriority {
        LOW,
        HIGH
    }

    private ArrayList<Command> requestedAdditionList = new ArrayList<Command>();
    private ArrayList<Command> loopedCommandList = new ArrayList<Command>();
    private Map<Command, CommandPriority> commandPriorityMap = new HashMap<Command, CommandPriority>();

    private Map<Mechanism, Command> mechanismLockingCommandMap = new HashMap<Mechanism, Command>();

    private ArrayList<Command> commandExecutionList = new ArrayList<Command>();
    private Map<Command, Boolean> commandInitializedMap = new HashMap<Command, Boolean>();

    private ArrayList<Command> removedCommandList = new ArrayList<Command>();

    public void add(Command command) {
        loopedCommandList.add(command);
        commandPriorityMap.put(command, CommandPriority.LOW);
    }

    public void requestCommandExecution(Command command) {
        if (!requestedAdditionList.contains(command)) {
            ArrayList<Command> requestedListRemovalList = new ArrayList<Command>();
            for (Command cachedCommand : requestedAdditionList) {
                if (!command.getBoundMechanisms().isEmpty()) {
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (cachedCommand.getBoundMechanisms().contains(mechanism)) {
                            requestedListRemovalList.add(cachedCommand);
                        }
                    }
                }
            }

            TelemetryHandler.getInstance().getTelemetry().addData("Requested Execution for", command.toString());
            requestedAdditionList.add(command);

            if (!commandPriorityMap.containsKey(command)) commandPriorityMap.put(command, CommandPriority.HIGH);

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
            if (commandInitializedMap.get(command) == true) {
                command.end();
            }

            TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Attempted to remove from Execution List");

            for (Mechanism boundMechanism : command.getBoundMechanisms()) if (mechanismLockingCommandMap.containsKey(boundMechanism)) mechanismLockingCommandMap.remove(boundMechanism);
            removedCommandList.add(command);
        }
    }

    public void run() {
        //Looped Command Checking
        if (!loopedCommandList.isEmpty()) {
            for (Command command : loopedCommandList) {
                boolean passedCheck = true;

                if (commandExecutionList.contains(command)) {
                    passedCheck = false;
                }

                if (!command.getBoundMechanisms().isEmpty()) {
                    TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Is checking its Mechanisms");
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(mechanism)) {
                            if (commandPriorityMap.get(command) == CommandPriority.LOW && commandPriorityMap.get(mechanismLockingCommandMap.get(mechanism)) == CommandPriority.HIGH) {
                                passedCheck = false;
                                TelemetryHandler.getInstance().getTelemetry().addData("Priority Conflict", "Detected");
                                break;
                            }
                        }
                    }
                }
                TelemetryHandler.getInstance().getTelemetry().addData("Did " + command.toString() + " pass its check?", passedCheck);
                if (passedCheck) {
                    for (Mechanism boundMechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(boundMechanism) && mechanismLockingCommandMap.get(boundMechanism) != null) {
                            mechanismLockingCommandMap.get(boundMechanism).end();
                            commandInitializedMap.remove(mechanismLockingCommandMap.get(boundMechanism));
                            commandExecutionList.remove(mechanismLockingCommandMap.get(boundMechanism));
                        }

                        mechanismLockingCommandMap.put(boundMechanism, command);
                        TelemetryHandler.getInstance().getTelemetry().addData(boundMechanism.toString() + "now bound to", command.toString());
                    }

                    commandExecutionList.add(command);
                    commandInitializedMap.put(command, false);
                }
            }
        }

        //Requested Command Checking
        if (!requestedAdditionList.isEmpty()) {
            ArrayList<Command> executedAndRemovedCommands = new ArrayList <Command>();
            for (Command command : requestedAdditionList) {
                boolean passedCheck = true;

                if (!command.getBoundMechanisms().isEmpty()) {
                    TelemetryHandler.getInstance().getTelemetry().addData(command.toString(), "Is checking its Mechanisms");
                    TelemetryHandler.getInstance().getTelemetry().update();
                    for (Mechanism mechanism : command.getBoundMechanisms()) {
                        TelemetryHandler.getInstance().getTelemetry().addData("Checking mechanism", mechanism.toString());
                        if (mechanismLockingCommandMap.containsKey(mechanism)) {
                            if (commandPriorityMap.get(command) == CommandPriority.LOW && commandPriorityMap.get(mechanismLockingCommandMap.get(mechanism)) == CommandPriority.HIGH) {
                                passedCheck = false;
                                TelemetryHandler.getInstance().getTelemetry().addData("Priority Conflict", "Detected");
                                break;
                            }
                        }
                    }
                }

                if (passedCheck) {
                    for (Mechanism boundMechanism : command.getBoundMechanisms()) {
                        if (mechanismLockingCommandMap.containsKey(boundMechanism)) {
                            mechanismLockingCommandMap.get(boundMechanism).end();
                            commandInitializedMap.remove(mechanismLockingCommandMap.get(boundMechanism));
                            commandExecutionList.remove(mechanismLockingCommandMap.get(boundMechanism));
                        }

                        mechanismLockingCommandMap.put(boundMechanism, command);
                        TelemetryHandler.getInstance().getTelemetry().addData(boundMechanism.toString() + " now bound to ", command.toString());
                    }

                    commandExecutionList.add(command);
                    commandInitializedMap.put(command, false);

                    executedAndRemovedCommands.add(command);
                }

                TelemetryHandler.getInstance().getTelemetry().addData("passedCheck", passedCheck);
            }
            requestedAdditionList.removeAll(executedAndRemovedCommands);
            executedAndRemovedCommands.clear();
        }

        if (!commandExecutionList.isEmpty()) {
            for (Command command : commandExecutionList) {
//                TelemetryHandler.getInstance().getTelemetry().addData("Executing", command.toString());

                if (commandInitializedMap.get(command) == false) {
                    command.initialize();
                    commandInitializedMap.put(command, true);
                }

                command.execute();

                if (command.isFinished()) {
                    command.end();

                    removedCommandList.add(command);
                }
            }

            for (Command removedCommand : removedCommandList) {
                commandInitializedMap.remove(removedCommand);
                for (Mechanism boundMechanism : removedCommand.getBoundMechanisms()) {
                    if (mechanismLockingCommandMap.containsKey(boundMechanism)) mechanismLockingCommandMap.remove(boundMechanism);
                }
            }

            commandExecutionList.removeAll(removedCommandList);
            removedCommandList.clear();
        }
    }

    public boolean isRunning(Command command) {
        return commandExecutionList.contains(command);
    }

    public void end() {
        for (Command command : loopedCommandList) {
            command.end();
        }
    }

    public boolean isEmpty() {
        return commandExecutionList.isEmpty();
    }

    public void postCommands(Telemetry telemetry) {
        for (Map.Entry<Mechanism, Command> entry : mechanismLockingCommandMap.entrySet()) {
            TelemetryHandler.getInstance().getTelemetry().addData(entry.getKey().toString() + "is bound by", entry.getValue().toString());
        }
        for (Command command : requestedAdditionList) {
            for (Mechanism mechanism : command.getBoundMechanisms()) {
                TelemetryHandler.getInstance().getTelemetry().addData(mechanism.toString(), "is bound by the requested : " + command.toString());
            }
        }
    }

    public void addButtonCommand(Button button, ButtonStateRule rule, Command wrappedCommand) {
        ButtonCommand localButtonCommand = new ButtonCommand(button, rule, wrappedCommand);
        commandExecutionList.add(localButtonCommand);
        commandInitializedMap.put(localButtonCommand, false);
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
