package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;

//CommandGroups inherit from Commands, but all the logic is internal.
public class ExampleCommandGroup extends CommandGroup {

    //All you need is the constructor, where you call methods relative to each other using addParallel and addSequential.
    public ExampleCommandGroup() {
        //Note that parallel blocks will run in parallel to the NEXT sequential in addition to just other parallel blocks, and the list will update dynamically as it runs.
        addParallel(new ExampleBlankCommand());
        addSequential(new ExampleBlankCommand());

        addSequential(new ExampleBlankCommand());
    }
}
