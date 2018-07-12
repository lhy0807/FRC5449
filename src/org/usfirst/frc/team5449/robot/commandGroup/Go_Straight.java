package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Go_Straight extends CommandGroup{
	public Go_Straight() {
		addParallel(new Initialize_block());
		addSequential(new DriveDistance(4.00));
	}

}
