package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToMid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Mid_Ready extends CommandGroup{
	public Mid_Ready(){
		addParallel(new IntakeOut());
		addSequential(new IntakeStop());
		addSequential(new Delay(0.4));
		addSequential(new LifterToMid());
	}
}
