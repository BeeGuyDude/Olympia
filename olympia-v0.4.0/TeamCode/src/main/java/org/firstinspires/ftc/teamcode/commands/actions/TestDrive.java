package org.firstinspires.ftc.teamcode.commands.actions;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;

public class TestDrive implements Command {
    private Axis leftInput;
    private Axis rightInput;

    private Gamepad gamepad;

    public TestDrive(Axis LeftInput, Axis RightInput) {
        this.leftInput = LeftInput;
        this.rightInput = RightInput;
    }

    public void initialize() {

    }

    public void execute() {
        setSpeeds(leftInput.get(), rightInput.get());
    }

    public boolean isFinished() {
        return false;
    }

    public void end() {

    }

    private void setSpeeds(double leftIn, double rightIn) {}

}
