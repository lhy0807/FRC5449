package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_Pos1_L_SCSW extends CommandGroup{
	public New_Auto_Pos1_L_SCSW(){
		addParallel(new Initialize_block());//1.25,0.4
		addSequential(new DriveDistance(6.8));
		addSequential(new TurnTo(-53.6));
		addSequential(new ScaleRelease(0.8));
		addSequential(new TurnTo(-160.3));
		addSequential(new IntakeOut());
		addSequential(new DriveDistance(2.43));
		addSequential(new IntakeIn());
		addSequential(new TurnTo(-180));
		addSequential(new Switch_Release(0.4));
	}
}
