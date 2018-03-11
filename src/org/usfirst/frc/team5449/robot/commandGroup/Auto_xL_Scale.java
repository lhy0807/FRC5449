package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_xL_Scale extends CommandGroup{
	@Deprecated
	public Auto_xL_Scale(){
		addParallel(new Initialize_block());
		addSequential(new DriveDistance(0.5));
		addSequential(new TurnTo(17.06));
		addSequential(new DriveDistance(3.08));
		addSequential(new TurnTo(0));
		addSequential(new DriveDistance(2.6));
		addSequential(new TurnTo(-45));
		addSequential(new ScaleRelease());
	}
}
