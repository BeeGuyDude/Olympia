package org.firstinspires.ftc.teamcode.commands;

public interface Command {

    public void initialize();

    public void execute();

    public Boolean isFinished();

    public void end();

}

