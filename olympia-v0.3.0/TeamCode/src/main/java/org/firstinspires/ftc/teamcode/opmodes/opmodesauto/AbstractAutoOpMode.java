package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

@Autonomous
public class AbstractAutoOpMode extends OpMode {

    @Override
    public void init() {
        FTCRuntimeHandler.setOpMode(this, OpModeType.AUTO);

        MechanismEngine.getInstance().robotRunning = true;
        MechanismEngine.getInstance().initializeMechanisms();

        telemetry.addData("bruh moment", "indeed");


    }

    public void loop() {}

    @Override
    public void stop() {
        MechanismEngine.getInstance().robotRunning = false;
    }
}
