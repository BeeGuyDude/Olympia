package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.TESTMECHANISM;

public class TestCommand implements Command {

    private TESTMECHANISM localMechanism = MechanismEngine.getInstance().getMechanism(TESTMECHANISM.class);

    public void initialize() {

    }

    public void execute() {
        localMechanism.testMethod();
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }

}
