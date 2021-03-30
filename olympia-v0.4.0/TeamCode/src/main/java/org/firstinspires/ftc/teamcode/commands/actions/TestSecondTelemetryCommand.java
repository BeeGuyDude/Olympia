package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.basecommands.Command;

public class TestSecondTelemetryCommand extends Command {
    private Telemetry localTelem;

    public TestSecondTelemetryCommand(Telemetry testTelem) {
        this.localTelem = testTelem;
    }

    public void initialize() {

    }

    public void execute() {
        localTelem.addData("Test", "YOOOOOOOOOOOOOOOOO");
    }

    public boolean isFinished() {
        return true;
    }

    public void end() {

    }
}
