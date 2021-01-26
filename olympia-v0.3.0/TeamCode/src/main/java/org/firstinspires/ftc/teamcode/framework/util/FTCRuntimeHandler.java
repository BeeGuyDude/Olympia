package org.firstinspires.ftc.teamcode.framework.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.framework.util.Constants.*;
import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

public class FTCRuntimeHandler {
    private static HardwareMap hardwareMap;
    private static OpMode opMode;
    private static Telemetry telemetry;

    private static FTCRuntimeHandler handlerInstance;
    public static OpModeType currentOpModeType;

    public static FTCRuntimeHandler getInstance() {
        if (handlerInstance == null) {
            handlerInstance = new FTCRuntimeHandler();
        }
        return handlerInstance;
    }

    public static void setOpMode(OpMode opMode, OpModeType type) {
        FTCRuntimeHandler.opMode = opMode;
        FTCRuntimeHandler.hardwareMap = opMode.hardwareMap;
        FTCRuntimeHandler.telemetry = opMode.telemetry;

        currentOpModeType = type;
    }

    public static HardwareMap getHardwareMap() {
        return hardwareMap;
    }

    public static OpMode getOpMode() {
        return opMode;
    }

    public static Telemetry getTelemetry() {
        return telemetry;
    }
}
