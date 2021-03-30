package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;

public class ExampleTelemetryCommand extends Command {
    private Telemetry localTelem;

    public ExampleTelemetryCommand(Telemetry testTelem) {
        this.localTelem = testTelem;
    }

    public void initialize() {

    }

    public void execute() {
        localTelem.addData("Test", "AYYYYYYYYYYYYYYYYYYYY");
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {

    }
}
