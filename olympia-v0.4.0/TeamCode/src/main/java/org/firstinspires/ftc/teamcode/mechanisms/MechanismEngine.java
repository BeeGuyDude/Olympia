package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.framework.util.Constants.*;
import org.firstinspires.ftc.teamcode.mechanisms.*;

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

            if(!getInstance().rawMechanismMap.containsKey(mechanismKey)) {

                T obj = mechanismKey.newInstance();

                getInstance().rawMechanismMap.put(mechanismKey, obj);
            }

            returnInstance = (T)getInstance().rawMechanismMap.get(mechanismKey);

        } catch (Exception e) {
            //Future Telemetry Post for "Cannot Instantiate"
        }

        return returnInstance;
    }

}

