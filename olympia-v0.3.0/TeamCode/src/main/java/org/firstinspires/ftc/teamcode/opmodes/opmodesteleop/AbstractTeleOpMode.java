package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;


abstract public class AbstractTeleOpMode extends OpMode {

    @Override
    public void init() {
        FTCRuntimeHandler.setOpMode(this, OpModeType.TELEOP);

        MechanismEngine.getInstance().robotRunning = true;
    }

    public void loop() {}

    @Override
    public void stop() {
        MechanismEngine.getInstance().robotRunning = false;
    }
}
