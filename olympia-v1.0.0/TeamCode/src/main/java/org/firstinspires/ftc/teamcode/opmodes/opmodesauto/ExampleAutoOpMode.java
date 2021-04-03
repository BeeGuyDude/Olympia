package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

//This little annotation thingy is important, it's what makes the OpMode come up on the phone
@Autonomous
public class ExampleAutoOpMode extends AutoOpModeWrapper {      //Note that you extend the wrapper, not just an OpMode

    //Commands to run when you press the init button go here; **FOR THE LOVE OF WAN DO NOT ADD A COMMAND THAT RETURNS [isFinished -> false] HERE, IT RUNS COMMANDS UNTIL THEY FINISH**
    @Override
    public void autoInit() {
//        scheduler.enableDebugTelemetry();

    }

    //Commands to run repeatedly until finished when you press the play button go here; for auto, I'd recommend scheduler.add()-ing a single CommandGroup for this, and writing the auto in there.
    @Override
    public void autoLoop() {

    }

}
