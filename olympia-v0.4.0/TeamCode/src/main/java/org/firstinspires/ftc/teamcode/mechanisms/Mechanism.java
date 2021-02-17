package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
public abstract class Mechanism {
    static String name = "Test";

    private static Mechanism mechanismInstance;

    abstract void init();

    public static String getName() {
        return name;
    }
}
