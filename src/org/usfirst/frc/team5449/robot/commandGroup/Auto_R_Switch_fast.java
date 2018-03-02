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

public class Auto_R_Switch_fast extends CommandGroup{
	public Auto_R_Switch_fast(){
		double[] target0 = {5.5,2.5};
		addParallel(new Initialize_block(0.5));
		addSequential(new DriveTo(target0,true));
		addSequential(new Delay(0.3));
		addSequential(new TurnTo(0));
		addSequential(new Switch_Release());
		addSequential(new TurnTo(-90));
		addSequential(new DriveDistance(1.2));
		addSequential(new TurnTo(0));
		addSequential(new DriveDistance(1.5));
	}
}
