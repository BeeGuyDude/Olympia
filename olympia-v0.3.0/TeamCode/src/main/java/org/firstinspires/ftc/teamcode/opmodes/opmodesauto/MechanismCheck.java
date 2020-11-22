package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

public class MechanismCheck extends OpMode {

    @Override
    public void init() {
        FTCRuntimeHandler.setOpMode(this);

        MechanismEngine.getInstance().robotRunning = true;
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        MechanismEngine.getInstance().robotRunning = false;
    }
}
