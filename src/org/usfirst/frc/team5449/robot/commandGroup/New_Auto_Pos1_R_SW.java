package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_Pos1_R_SW extends CommandGroup{
	public New_Auto_Pos1_R_SW(){
		addParallel(new Initialize_block());//1.25,0.4
		addSequential(new DriveDistance(1.2));//1.25,1.6
		addSequential(new TurnTo(-90));
		addSequential(new DriveDistance(4.2));//5.45,1.6
		addSequential(new TurnTo(0));
		addSequential(new Switch_Release(1.95));//5.45,3.55
	}
}
