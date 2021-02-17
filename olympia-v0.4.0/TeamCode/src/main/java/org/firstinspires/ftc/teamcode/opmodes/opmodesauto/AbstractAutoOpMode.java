package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

@Autonomous
public class AbstractAutoOpMode extends OpMode {

    @Override
    public void init() {

        telemetry.addData("bruh moment", "indeed");


    }

    public void loop() {}

    @Override
    public void stop() {}
}
