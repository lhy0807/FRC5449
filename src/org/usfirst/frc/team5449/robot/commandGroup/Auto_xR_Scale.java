package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_xR_Scale extends CommandGroup{
	@Deprecated
	public Auto_xR_Scale(){
		addParallel(new Initialize_block());
		addSequential(new DriveDistance(0.5));//2.3,0.9
		addSequential(new TurnTo(17.06));
		addSequential(new DriveDistance(3.08));//1.4,3.84
		addSequential(new TurnTo(0));
		addSequential(new DriveDistance(1.9));//1.4,5.74
		addSequential(new TurnTo(-90));
		addSequential(new DriveDistance(4.85));
		addSequential(new TurnTo(10));
		addSequential(new ScaleRelease(1.4));
	}
}
