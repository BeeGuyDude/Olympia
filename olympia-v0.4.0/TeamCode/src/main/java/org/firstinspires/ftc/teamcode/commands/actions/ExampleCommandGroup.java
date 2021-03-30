package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;

public class ExampleCommandGroup extends CommandGroup {

    public ExampleCommandGroup() {
        addParallel(new ExampleBlankCommand());
        addSequential(new ExampleBlankCommand());

        addSequential(new ExampleBlankCommand());
    }
}
