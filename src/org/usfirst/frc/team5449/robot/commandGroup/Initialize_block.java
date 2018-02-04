package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.Lifterbump;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Initialize_block extends CommandGroup{
	public Initialize_block(){
		addParallel(new Lifterbump());
		addSequential(new IntakeOut());
        addSequential(new IntakeStop());
        addSequential(new Delay(0.3));
        addSequential(new LifterToDown());
        addSequential(new IntakeIn(0.7));
	}
}
