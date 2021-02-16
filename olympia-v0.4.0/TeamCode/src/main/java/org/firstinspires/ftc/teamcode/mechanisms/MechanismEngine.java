package org.firstinspires.ftc.teamcode.mechanisms;

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

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable);
    }

    public void addMechanism(Mechanism inputMechanism) {
        MechanismIndex.add(inputMechanism);
    }
    
}
