package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_Pos1_L_SW extends CommandGroup{
	public New_Auto_Pos1_L_SW(){
		addParallel(new Initialize_block());
		addSequential(new DriveDistance(3.81));
		addSequential(new TurnTo(-90));
		addSequential(new Switch_Release(0.6));
	}
}
