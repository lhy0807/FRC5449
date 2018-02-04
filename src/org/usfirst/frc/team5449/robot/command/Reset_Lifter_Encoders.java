package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Reset_Lifter_Encoders extends Command{

	@Override
	protected void initialize() {
		Robot.lifter.ResetEncoders();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
