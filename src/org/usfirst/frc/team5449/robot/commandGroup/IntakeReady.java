package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.IntakeIn2;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeReady extends CommandGroup{
	public IntakeReady(){
		addSequential(new IntakeOut());
        addSequential(new IntakeStop()); 
        addSequential(new LifterToDown());

	}
}
