package org.firstinspires.ftc.teamcode.commands.actions;

import org.firstinspires.ftc.teamcode.commands.basecommands.CommandGroup;

public class TestCommandGroup extends CommandGroup {

    public TestCommandGroup() {
        addParallel(new TestCommand());
        addSequential(new TestCommand());
        addSequential(new TestCommand());
    }
}
