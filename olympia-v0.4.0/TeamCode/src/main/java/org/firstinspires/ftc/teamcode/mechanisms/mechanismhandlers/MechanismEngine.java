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

    //Mechanism table handling
    private Map<Class, Object> rawMechanismMap = new HashMap<Class, Object>();
    public <T> T getMechanism(Class<T> mechanismKey) {
        T returnInstance = null;

        try {

            if (!getInstance().rawMechanismMap.containsKey(mechanismKey)) {

                T obj = mechanismKey.newInstance();

                getInstance().rawMechanismMap.put(mechanismKey, obj);
            }

            returnInstance = (T) getInstance().rawMechanismMap.get(mechanismKey);

        } catch (Exception e) {
            //Future Telemetry Post for "Cannot Instantiate"
        }

        return returnInstance;
    }

    //Mechanism Initialization Handling
    private ArrayList<Object> initializedMechanisms = new ArrayList<>();
    private HardwareMap localHardwareMap;

    public void setHardwareMap(HardwareMap hwmap) {
        this.localHardwareMap = hwmap;
    }

    public void initializeMechanisms() {
        for (Object mechanism : rawMechanismMap.values()) {
            Mechanism castedMechanism = (Mechanism) mechanism;

            if (!initializedMechanisms.contains(mechanism)) {
                try {
                    castedMechanism.init(localHardwareMap);
                    initializedMechanisms.add(mechanism);
                } catch (Exception e) {
                    //future telemetryhandler post for improper cast (signifying wrong object)
                }
            }
        }
    }

}

