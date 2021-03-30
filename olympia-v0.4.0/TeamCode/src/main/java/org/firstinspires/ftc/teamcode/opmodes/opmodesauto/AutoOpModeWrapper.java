package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.framework.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

abstract class AutoOpModeWrapper extends OpMode {

    CommandScheduler scheduler = new CommandScheduler();
    private Timekeeper timekeeper = new Timekeeper();

    @Override
    public void init() {
        autoInit();
        MechanismEngine.getInstance().setHardwareMap(hardwareMap);
        MechanismEngine.getInstance().initializeMechanisms();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        autoLoop();
        MechanismEngine.getInstance().initializeMechanisms();
        //yes I know it does it twice, you don't know if some mechanisms aren't used yet until the loop portion

        scheduler.scrubCommands();

        telemetry.addData("Initialization phase", "Succeeded.");
    }
    public abstract void autoInit();

    @Override
    public void loop() {
        telemetry.addData("Cycle time", timekeeper.getCycleTime() + "ms");
        telemetry.addData("Average Cycle Time", timekeeper.getAverageCycleTime() + "ms");
        timekeeper.update(getRuntime());

        scheduler.run();
    }
    public abstract void autoLoop();

    @Override
    public void stop() {
        scheduler.end();
        scheduler.scrubCommands();
    }
}
