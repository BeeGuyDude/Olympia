package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.Command;
import org.firstinspires.ftc.teamcode.mechanisms.mechanismhandlers.MechanismEngine;

//This is an abstract class and not an interface because it needs to be able to store Mechanisms.
public class ExampleBlankCommand extends Command {

    //These are the methods every command will have.
    //Initialize runs once when the command starts running.
    public void initialize() {

    }

    //Execute runs every time the scheduler cycles until the isFinished returns true.
    public void execute() {

    }

    //isFinished is the condition for whether the command has finished or not yet, like if a sensor has passed a certain threshold or something.
    //Note: commands that return true for isFinished() will run in a single cycle, so this should only be used for data modifying commands and not device modifying commands.
    public boolean isFinished() {
        return true;
    }

    //End runs once, when the command isFinished() condition returns true.
    public void end() {

    }

}
