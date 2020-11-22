package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
public abstract class Mechanism implements Runnable{

    abstract void init();

    void registerSelf() {
        MechanismEngine.getInstance().addMechanism(this);
    }

    void getName() {}
}
