package org.firstinspires.ftc.teamcode.framework.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class FTCRuntimeHandler {
    private static HardwareMap hardwareMap;
    private static OpMode opMode;
    private static Telemetry telemetry;

    public static HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    public static void setOpMode(OpMode opMode) {
        FTCRuntimeHandler.opMode = opMode;
        FTCRuntimeHandler.hardwareMap = opMode.hardwareMap;
        FTCRuntimeHandler.telemetry = opMode.telemetry;
    }

    public static OpMode getOpMode() {
        return opMode;
    }

    public static Telemetry getTelemetry() {
        return telemetry;
    }
}
