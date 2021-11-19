package org.firstinspires.ftc.teamcode.opmodes.opmodesteleop;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.CommandScheduler;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.framework.controllers.Axis;
import org.firstinspires.ftc.teamcode.framework.controllers.Button;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

import static org.firstinspires.ftc.teamcode.framework.util.FrameworkConstants.*;

abstract class TeleOpModeWrapper extends OpMode {

    CommandScheduler scheduler = new CommandScheduler();
    Timekeeper timekeeper = new Timekeeper();

    //I apologize for this abomination
    //Driver
    public Button DriverAButton;
    public Button DriverBButton;
    public Button DriverXButton;
    public Button DriverYButton;
    public Button DriverLeftBumper;
    public Button DriverRightBumper;
    public Button DriverDPadUp;
    public Button DriverDPadRight;
    public Button DriverDPdDown;
    public Button DriverDPadLeft;
    public Button DriverBackButton;
    public Button DriverStartButton;
    public Button DriverCenterButton;

    public Axis DriverLeftXAxis;
    public Axis DriverLeftYAxis;
    public Axis DriverRightXAxis;
    public Axis DriverRightYAxis;
    public Axis DriverLeftTrigger;
    public Axis DriverRightTrigger;

    //Operator
    public Button OperatorAButton;
    public Button OperatorBButton;
    public Button OperatorXButton;
    public Button OperatorYButton;
    public Button OperatorLeftBumper;
    public Button OperatorRightBumper;
    public Button OperatorDPadUp;
    public Button OperatorDPadRight;
    public Button OperatorDPadDown;
    public Button OperatorDPadLeft;
    public Button OperatorBackButton;
    public Button OperatorStartButton;
    public Button OperatorCenterButton;

    public Axis OperatorLeftXAxis;
    public Axis OperatorLeftYAxis;
    public Axis OperatorRightXAxis;
    public Axis OperatorRightYAxis;
    public Axis OperatorLeftTrigger;
    public Axis OperatorRightTrigger;
    
    private OpenCvPipeline cvPipeline = null;
    public OpenCvWebcam camera = null;
    
    public final void initCvPipeline(HardwareMap hwmap, String cameraName, int width, int height, boolean useMonitor) {
        cvPipeline = new OpenCvPipeline() {
            @Override public final synchronized Mat processFrame(Mat input) {
                return TeleOpModeWrapper.this.cameraLoop(input);
            }
        };
        if(useMonitor) {
            int cameraMonitorViewId = hwmap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hwmap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(hwmap.get(WebcamName.class,cameraName), cameraMonitorViewId);
        } else {
            camera = OpenCvCameraFactory.getInstance().createWebcam(hwmap.get(WebcamName.class,cameraName));
        }
        camera.setPipeline(cvPipeline);
        camera.setMillisecondsPermissionTimeout(250);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override public void onOpened() {
                camera.startStreaming(width, height, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("Camera streaming error with code",errorCode);
            }
        });
    }
    public synchronized Mat cameraLoop(Mat input) {
        //Do nothing by default
        return null;
    }

    @Override
    public final void init() {

        //I wanted to do this in line with the declarations, but FTC is dumb and sets the Gamepad references to null until the init() block so here we are in constructor hell
        DriverAButton = new Button(gamepad1, ButtonID.A_BUTTON, scheduler);
        DriverBButton = new Button(gamepad1, ButtonID.B_BUTTON, scheduler);
        DriverXButton = new Button(gamepad1, ButtonID.X_BUTTON, scheduler);
        DriverYButton = new Button(gamepad1, ButtonID.Y_BUTTON, scheduler);
        DriverLeftBumper = new Button(gamepad1, ButtonID.LEFT_BUMPER, scheduler);
        DriverRightBumper = new Button(gamepad1, ButtonID.RIGHT_BUMPER, scheduler);
        DriverDPadUp = new Button(gamepad1, ButtonID.UP, scheduler);
        DriverDPadRight = new Button(gamepad1, ButtonID.RIGHT, scheduler);
        DriverDPdDown = new Button(gamepad1, ButtonID.DOWN, scheduler);
        DriverDPadLeft = new Button(gamepad1, ButtonID.LEFT, scheduler);
        DriverBackButton = new Button(gamepad1, ButtonID.BACK, scheduler);
        DriverStartButton = new Button(gamepad1, ButtonID.START, scheduler);
        DriverCenterButton = new Button(gamepad1, ButtonID.CENTER, scheduler);

        DriverLeftXAxis = new Axis(gamepad1, AxisID.LEFT_X);
        DriverLeftYAxis = new Axis(gamepad1, AxisID.LEFT_Y);
        DriverRightXAxis = new Axis(gamepad1, AxisID.RIGHT_X);
        DriverRightYAxis = new Axis(gamepad1, AxisID.RIGHT_Y);
        DriverLeftTrigger = new Axis(gamepad1, AxisID.LEFT_TRIGGER);
        DriverRightTrigger = new Axis(gamepad1, AxisID.RIGHT_TRIGGER);

        //Operator
        OperatorAButton = new Button(gamepad2, ButtonID.A_BUTTON, scheduler);
        OperatorBButton = new Button(gamepad2, ButtonID.B_BUTTON, scheduler);
        OperatorXButton = new Button(gamepad2, ButtonID.X_BUTTON, scheduler);
        OperatorYButton = new Button(gamepad2, ButtonID.Y_BUTTON, scheduler);
        OperatorLeftBumper = new Button(gamepad2, ButtonID.LEFT_BUMPER, scheduler);
        OperatorRightBumper = new Button(gamepad2, ButtonID.RIGHT_BUMPER, scheduler);
        OperatorDPadUp = new Button(gamepad2, ButtonID.UP, scheduler);
        OperatorDPadRight = new Button(gamepad2, ButtonID.RIGHT, scheduler);
        OperatorDPadDown = new Button(gamepad2, ButtonID.DOWN, scheduler);
        OperatorDPadLeft = new Button(gamepad2, ButtonID.LEFT, scheduler);
        OperatorBackButton = new Button(gamepad2, ButtonID.BACK, scheduler);
        OperatorStartButton = new Button(gamepad2, ButtonID.START, scheduler);
        OperatorCenterButton = new Button(gamepad2, ButtonID.CENTER, scheduler);

        OperatorLeftXAxis = new Axis(gamepad2, AxisID.LEFT_X);
        OperatorLeftYAxis = new Axis(gamepad2, AxisID.LEFT_Y);
        OperatorRightXAxis = new Axis(gamepad2, AxisID.RIGHT_X);
        OperatorRightYAxis = new Axis(gamepad2, AxisID.RIGHT_Y);
        OperatorLeftTrigger = new Axis(gamepad2, AxisID.LEFT_TRIGGER);
        OperatorRightTrigger = new Axis(gamepad2, AxisID.RIGHT_TRIGGER);

//        gamepad1.setJoystickDeadzone(CONTROLLER_1_DEADZONE); //I couldn't find a method for this under ftc 7.0.0
//        gamepad2.setJoystickDeadzone(CONTROLLER_2_DEADZONE);

        TelemetryHandler.getInstance().setTelemetry(telemetry);

        MechanismEngine.getInstance().refreshInstance();
        MechanismEngine.getInstance().setHardwareMap(hardwareMap);

        teleOpInit();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        scheduler.scrubCommands();
        scheduler.beginCheckingCommands();

        teleOpLoop();

        telemetry.addData("Initialization phase", "Succeeded.");
        telemetry.addData("WARNING","unable to set controller deadzones in init.");
    }
    public abstract void teleOpInit();

    @Override
    public final void loop() {
        telemetry.addData("Cycle Time", timekeeper.getCycleTime() + "ms");
        telemetry.addData("Average Cycle Time", timekeeper.getAverageCycleTime() + "ms");
        timekeeper.update(getRuntime());
        scheduler.run();
    }
    public abstract void teleOpLoop();

    @Override
    public final void stop() {
        scheduler.stopCheckingCommands();
        scheduler.end();
        if(camera!=null) {
            camera.stopStreaming();
        }
        //I know it removes them when the OpMode stops, I'm just paranoid.
        scheduler.scrubCommands();
    }
}
