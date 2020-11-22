package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.framework.util.FTCRuntimeHandler;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import java.util.concurrent.ThreadFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MechanismEngine implements ThreadFactory {

    public volatile boolean robotRunning = false; //Volatile means the same variable is accessed by all threads

    private static MechanismEngine engineInstance;

    private ArrayList<Mechanism> MechanismIndex = new ArrayList<>();

    public static MechanismEngine getInstance() {
        if (engineInstance == null) {
            engineInstance = new MechanismEngine();
        }
        return engineInstance;
    }

    @Override
    public Thread newThread(Runnable inputMechanism) {
        return new Thread(inputMechanism) {
//            @Override
//            public void run() {
//                while (MechanismEngine.getInstance().robotRunning) {
//                    try {
//
//                    } catch (Exception e) {
//
//                    }
//                }
//            }
        };
    }

    public void initializeMechanisms() {
        switch(FTCRuntimeHandler.currentOpModeType) {
            case TELEOP:
                for (Mechanism mechanism : MechanismIndex) {
                    newThread(mechanism).start();
                }
                break;
            case AUTO:

                break;
        }

    }

    public void addMechanism(Mechanism inputMechanism) {
        MechanismIndex.add(inputMechanism);
    }
}
