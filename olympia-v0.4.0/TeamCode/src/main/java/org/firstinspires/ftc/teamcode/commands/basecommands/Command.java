package org.firstinspires.ftc.teamcode.commands.basecommands;

public abstract class Command {

    public abstract void initialize();

    public abstract void execute();

    public abstract boolean isFinished();

    public abstract void end();

}

