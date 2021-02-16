package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;

public class TestMechanism extends Mechanism {

    private TestMechanism() { registerSelf(); }
    
    @Override
    public void init() {
        FTCRuntimeHandler.getInstance().getTelemetry().addData("Initialized?", "YEEEEEEEEEEEEEE");
    }

    @Override
    public void run() {
        FTCRuntimeHandler.getInstance().getTelemetry().addData("Running?", "OH YEAH WE RUNNIN");
    }
}
