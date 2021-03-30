package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.commands.actions.TestSecondTelemetryCommand;
import org.firstinspires.ftc.teamcode.commands.actions.TestTelemetryCommand;

@Autonomous
public class TestAuto extends AutoOpModeWrapper {

    @Override
    public void autoInit() {
        scheduler.add(new TestTelemetryCommand(telemetry));
    }

    @Override
    public void autoLoop() {
        scheduler.add(new TestSecondTelemetryCommand(telemetry));
    }

    @Override
    public void autoStop() {

    }
}
