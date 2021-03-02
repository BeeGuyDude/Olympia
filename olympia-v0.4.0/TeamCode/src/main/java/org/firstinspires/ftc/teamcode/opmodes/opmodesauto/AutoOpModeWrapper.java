package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;

abstract class AutoOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    @Override
    public void init() {


        autoInit();
    }
    public abstract void autoInit();

    @Override
    public void loop() {


        autoLoop();
    }
    public abstract void autoLoop();

    @Override
    public void stop() {


        autoStop();
    }
    public abstract void autoStop();
}
