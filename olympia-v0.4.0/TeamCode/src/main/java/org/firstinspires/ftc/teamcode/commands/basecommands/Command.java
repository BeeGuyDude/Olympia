package org.firstinspires.ftc.teamcode.commands.basecommands;

import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.Mechanism;

import java.util.ArrayList;

public abstract class Command {

    //Mechanism interrupt Binding
    private ArrayList<Mechanism> boundMechanisms = new ArrayList<Mechanism>();
    public void Requires(Mechanism mechanism) {
        boundMechanisms.add(mechanism);
    }
    public ArrayList<Mechanism> getBoundMechanisms() {
        return boundMechanisms;
    }

    public abstract void initialize();
    public abstract void execute();
    public abstract boolean isFinished();
    public abstract void end();
}

