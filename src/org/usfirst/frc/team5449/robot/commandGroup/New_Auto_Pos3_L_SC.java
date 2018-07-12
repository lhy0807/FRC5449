package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_Pos3_L_SC extends CommandGroup{
	public New_Auto_Pos3_L_SC(){
		addParallel(new Initialize_block(0.6));//1.25,0.4
		addSequential(new DriveDistance(5.30));
		addSequential(new TurnTo(90));
		addSequential(new DriveDistance(5.0));
		addSequential(new TurnTo(-5,1));
		addSequential(new ScaleRelease(1.4));
	}
}
