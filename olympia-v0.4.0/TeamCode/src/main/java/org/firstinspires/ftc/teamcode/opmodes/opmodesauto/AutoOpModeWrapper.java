package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.*;

abstract class AutoOpModeWrapper extends OpMode {

    protected CommandScheduler scheduler = new CommandScheduler();

    @Override
    public void init() {
        autoInit();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        autoLoop();
    }
    public abstract void autoInit();

    @Override
    public void loop() {
        scheduler.run();
    }
    public abstract void autoLoop();

    @Override
    public void stop() {
        autoStop();
        scheduler.end();
    }
    public abstract void autoStop();
}
