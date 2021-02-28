package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.Command;
import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.commands.SteppedCommand;
import org.firstinspires.ftc.teamcode.commands.actions.TestCommand;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
import org.firstinspires.ftc.teamcode.mechanisms.devicehandlers.DeadzonedController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.firstinspires.ftc.teamcode.framework.util.Constants.*;

abstract class TeleOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    @Override
    public void init() {
        gamepad1.setJoystickDeadzone(CONTROLLER_1_DEADZONE);

        wrapperInit();
    }
    public abstract void wrapperInit();

    @Override
    public void loop() {


        wrapperLoop();
    }
    public abstract void wrapperLoop();

    @Override
    public void stop() {


        wrapperStop();
    }
    public abstract void wrapperStop();
}
