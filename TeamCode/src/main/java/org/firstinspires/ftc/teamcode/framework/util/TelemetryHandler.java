package org.firstinspires.ftc.teamcode.framework.util;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TelemetryHandler {
    private Telemetry telemetry;
    private static TelemetryHandler handlerInstance;

    public static TelemetryHandler getInstance() {
        if (handlerInstance == null) {
            handlerInstance = new TelemetryHandler();
        }
        return handlerInstance;
    }
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }
}
