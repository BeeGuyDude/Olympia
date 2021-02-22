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

public abstract class TeleOpModeWrapper extends OpMode {

    public CommandScheduler scheduler = new CommandScheduler();

    private DeadzonedController DriverController = new DeadzonedController(gamepad1, CONTROLLER_1_DEADZONE);
    private DeadzonedController OperatorController = new DeadzonedController(gamepad2, CONTROLLER_2_DEADZONE);

    @Override
    public void init() {
//        scheduler.addLooped(new SteppedCommand());
    }

    @Override
    public void loop() {

    }

    public void stop() {}
}
