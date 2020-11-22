package org.firstinspires.ftc.teamcode.framework.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FTCRuntimeHandler {
    private static HardwareMap hardwareMap;
    private static OpMode opMode;

    public static HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    public static void setOpMode(OpMode opMode) {
        FTCRuntimeHandler.opMode = opMode;
        FTCRuntimeHandler.hardwareMap = opMode.hardwareMap;
    }

    public static OpMode getOpMode() {
        return opMode;
    }
}
