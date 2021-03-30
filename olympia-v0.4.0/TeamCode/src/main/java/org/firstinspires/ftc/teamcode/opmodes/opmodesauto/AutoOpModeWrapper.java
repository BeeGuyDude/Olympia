package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

abstract class AutoOpModeWrapper extends OpMode {

    private double previousCycleTime = 0;
    CommandScheduler scheduler = new CommandScheduler();

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
        telemetry.addData("Cycle time", (getRuntime() - previousCycleTime)*1000 + "ms");
        previousCycleTime = getRuntime();

        scheduler.run();
    }
    public abstract void autoLoop();

    @Override
    public void stop() {
        scheduler.end();
        scheduler.scrubCommands();

        autoStop();
    }
    public abstract void autoStop();
}
