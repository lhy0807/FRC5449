package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.DriveTo;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Auto_R_Scale_fast extends CommandGroup{
	public Auto_R_Scale_fast(){
		double[] target0 = {1.5,3.5};
		double[] target1 = {1.5,5.2};
		double[] target2 = {7,5.8};//6.2,8.2
		addParallel(new Initialize_block(0.6));
		addSequential(new DriveTo(target0,false));
		addSequential(new DriveTo(target1,true));
		addSequential(new DriveTo(target2,true));
		addSequential(new Delay(0.3));
		addSequential(new TurnTo(20.0));
		addSequential(new ScaleRelease(1.6));
		addSequential(new TurnTo(180));
		
	}
}
