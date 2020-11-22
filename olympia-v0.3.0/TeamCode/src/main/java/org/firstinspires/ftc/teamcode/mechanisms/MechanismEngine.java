package org.firstinspires.ftc.teamcode.mechanisms;

import java.util.ArrayList;

public class MechanismEngine {
    private static MechanismEngine engineInstance;

    private ArrayList<Mechanism> MechanismIndex = new ArrayList<>();

    public static MechanismEngine getInstance() {
        if (engineInstance == null) {
            engineInstance = new MechanismEngine();
        }
        return engineInstance;
    }

    public void addMechanism(Mechanism inputMechanism) {
        MechanismIndex.add(inputMechanism);
    }

    public void initializeMechanisms() {
        for (Mechanism mechanism : MechanismIndex) {
            mechanism.init();
        }
    }
}
