package org.firstinspires.ftc.teamcode.commands.basecommands;

public interface Command {

    public void initialize();

    public void execute();

    public boolean isFinished();

    public void end();

}

