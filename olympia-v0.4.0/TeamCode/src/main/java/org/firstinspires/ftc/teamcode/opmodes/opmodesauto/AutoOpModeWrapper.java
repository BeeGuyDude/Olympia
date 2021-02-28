package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

abstract class AutoOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    @Override
    public void init() {


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
