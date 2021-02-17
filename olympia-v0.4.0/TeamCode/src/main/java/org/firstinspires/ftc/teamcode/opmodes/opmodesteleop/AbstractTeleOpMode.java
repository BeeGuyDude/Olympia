package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import org.firstinspires.ftc.teamcode.mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

@TeleOp
public class AbstractTeleOpMode extends OpMode {
    MechanismEngine engine = new MechanismEngine();

    @Override
    public void init() {
        engine.instantiate(telemetry);
        telemetry.addData("Test", "yes");
    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {}
}
