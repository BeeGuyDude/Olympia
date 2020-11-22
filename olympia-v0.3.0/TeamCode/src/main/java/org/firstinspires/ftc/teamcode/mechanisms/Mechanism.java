package org.firstinspires.ftc.teamcode.mechanisms;

import org.firstinspires.ftc.teamcode.mechanisms.MechanismEngine;
abstract class Mechanism {

    abstract void init();

    void registerSelf() {
        MechanismEngine.getInstance().addMechanism(this);
    }

    void getName() {}
}
