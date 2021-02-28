package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class MechanismEngine {

    private static MechanismEngine engineInstance;

    public static MechanismEngine getInstance() {
        if (engineInstance == null) {
            engineInstance = new MechanismEngine();
        }
        return engineInstance;
    }

    public void instantiate(Telemetry telemetry) {

    }

//    public void addMechanism(List inputMechanism) {
//        MechanismIndex.add(inputMechanism.getClass());
//    }

//    public Mechanism getMechanism(String key) {
//        if (MechanismIndex.containsKey(key)) {
//            return MechanismIndex.get(key);
//        } else {
//            return null;
//        }
//    }

}
