package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_PosMid_R_SW extends CommandGroup{
	public New_Auto_PosMid_R_SW(){
		addParallel(new Initialize_block(0.8));
		addSequential(new DriveDistance(0.5));
		addSequential(new TurnTo(-40));
		addSequential(new DriveDistance(2.20));
		addSequential(new TurnTo(0));
		addSequential(new Switch_Release(1.4));
		/*
		addSequential(new TurnTo(-90));
		addSequential(new DriveDistance(1.63));
		addSequential(new TurnTo(0));
		addSequential(new DriveDistance(2.50,9,1));
		*/
	}
}
