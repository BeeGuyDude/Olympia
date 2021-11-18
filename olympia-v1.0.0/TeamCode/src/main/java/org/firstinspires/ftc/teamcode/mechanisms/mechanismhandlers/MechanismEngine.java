package org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers;

import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class MechanismEngine {

    //Singleton instance creation
    private static MechanismEngine engineInstance;
    public static MechanismEngine getInstance() {
        if (engineInstance == null) {
            engineInstance = new MechanismEngine();
        }
        return engineInstance;
    }

    public void refreshInstance() {
        rawMechanismMap.clear();
        localHardwareMap = null;
    }

    //Mechanism table handling
    private Map<Class, Object> rawMechanismMap = new HashMap<Class, Object>();
    public <T> T getMechanism(Class<T> mechanismKey) {
        T returnInstance = null;

        try {
            if (!getInstance().rawMechanismMap.containsKey(mechanismKey)) {
                T obj = mechanismKey.newInstance();
                getInstance().rawMechanismMap.put(mechanismKey, obj);

                try {
                    Mechanism castedMechanism = (Mechanism) obj;
                    castedMechanism.init(localHardwareMap);
                } catch (Exception e) {
                    //Future TelemetryHandler warning for "Improper Mechanism Cast"
                }
            }
            returnInstance = (T) getInstance().rawMechanismMap.get(mechanismKey);
        } catch (Exception e) {
            //Future Telemetry Post for "Cannot Instantiate"
        }

        return returnInstance;
    }

    //Mechanism Initialization Handling
    private HardwareMap localHardwareMap;
    public void setHardwareMap(HardwareMap hwmap) {
        this.localHardwareMap = hwmap;
    }
}

