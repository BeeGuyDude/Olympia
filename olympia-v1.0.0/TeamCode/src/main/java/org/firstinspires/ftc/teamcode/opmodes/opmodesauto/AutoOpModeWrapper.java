package org.firstinspires.ftc.teamcode.opmodes.opmodesauto;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.commands.*;
import org.firstinspires.ftc.teamcode.framework.util.TelemetryHandler;
import org.firstinspires.ftc.teamcode.framework.util.Timekeeper;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

abstract class AutoOpModeWrapper extends OpMode {
    private OpenCvPipeline cvPipeline = null;
    public OpenCvWebcam camera = null;

    CommandScheduler scheduler = new CommandScheduler();
    Timekeeper timekeeper = new Timekeeper();
    
    public final void initCvPipeline(HardwareMap hwmap, String cameraName, int width, int height, boolean useMonitor) {
        cvPipeline = new OpenCvPipeline() {
            @Override public final synchronized Mat processFrame(Mat input) {
                return AutoOpModeWrapper.this.cameraLoop(input);
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
        TelemetryHandler.getInstance().setTelemetry(telemetry);

        MechanismEngine.getInstance().refreshInstance();
        MechanismEngine.getInstance().setHardwareMap(hardwareMap);

        autoInit();

        while (!scheduler.isEmpty()) {
            scheduler.run();
        }

        scheduler.scrubCommands();

        autoLoop();

        telemetry.addData("Initialization phase", "Succeeded.");
    }
    public abstract void autoInit();

    @Override
    public final void loop() {
        telemetry.addData("Cycle time", timekeeper.getCycleTime() + "ms");
        telemetry.addData("Average Cycle Time", timekeeper.getAverageCycleTime() + "ms");
        timekeeper.update(getRuntime());

        scheduler.run();
    }
    public abstract void autoLoop();

    @Override
    public final void stop() {
        scheduler.end();
        if(camera!=null) {
            camera.stopStreaming();
        }
        //I know it removes them automatically when the OpMode ends, I'm just paranoid.
        scheduler.scrubCommands();
    }
}
