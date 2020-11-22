package org.firstinspires.ftc.teamcode.mechanisms;

import java.util.concurrent.ThreadFactory;
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
    public Thread newThread(Runnable r) {
        return new Thread(r) {
            @Override
            public void run() {
                while (MechanismEngine.getInstance().robotRunning) {
                    try {

                    } catch (Exception e) {

                    }
                }
            }
        };
    }

    public void addMechanism(Mechanism inputMechanism) {
        MechanismIndex.add(inputMechanism);
    }

    public void initializeMechanisms() {
        for (Mechanism mechanism : MechanismIndex) {
            newThread(mechanism).start();
        }
    }
}
