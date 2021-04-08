package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

abstract class AutoOpModeWrapper extends OpMode {

    CommandScheduler scheduler = new CommandScheduler();
    Timekeeper timekeeper = new Timekeeper();

    @Override
    public void init() {
        TelemetryHandler.getInstance().setTelemetry(telemetry);
        MechanismEngine.getInstance().refreshInstance();

        autoInit();

        MechanismEngine.getInstance().setHardwareMap(hardwareMap);

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        scheduler.scrubCommands();

        autoLoop();

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

        //I know it removes them automatically when the OpMode ends, I'm just paranoid.
        scheduler.scrubCommands();
    }
}
