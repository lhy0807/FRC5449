package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeIn2;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class New_Auto_Pos1_L_SC extends CommandGroup{
	public New_Auto_Pos1_L_SC(){
		addParallel(new Initialize_block());//1.25,0.4
		addSequential(new DriveDistance(6.6));
		addSequential(new TurnTo(-53.6));
		addSequential(new ScaleRelease(1.0));
		addSequential(new TurnTo(-163.7));
		addSequential(new IntakeOut());
		addSequential(new DriveDistance(2.2));
		addSequential(new IntakeIn(2));
		addSequential(new DriveDistance(-1.2));
		addSequential(new TurnTo(16.3));
		addSequential(new DriveDistance(0.5));
		addSequential(new TurnTo(-53.6));
		addSequential(new ScaleRelease(0.5));
	}
}
