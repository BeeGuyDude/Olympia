package org.firstinspires.ftc.teamcode.framework;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;

public class TelemetryHandler {
    private static TelemetryHandler handlerInstance;

    public static TelemetryHandler getInstance() {
        if (handlerInstance == null) {
            handlerInstance = new TelemetryHandler();
        }
        return handlerInstance;
    }
}
