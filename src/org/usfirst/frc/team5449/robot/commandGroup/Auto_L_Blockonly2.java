package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_L_Blockonly2 extends CommandGroup{
	public Auto_L_Blockonly2(){
		addSequential(new Initialize_block());
		addSequential(new DriveDistance(2.1));
		addSequential(new Delay(0.5));
		addSequential(new Switch_Release());
		addSequential(new DriveDistance(-0.5));
		addSequential(new TurnTo(90));
		addSequential(new DriveDistance(1));
		addSequential(new TurnTo(0));
		addSequential(new DriveDistance(1.5));
	}
}
