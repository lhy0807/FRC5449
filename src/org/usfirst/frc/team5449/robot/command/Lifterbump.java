package org.usfirst.frc.team5449.robot.command;


import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Lifterbump extends Command{
	public Lifterbump(){
		requires(Robot.lifter);
	}
	private Timer timer;
	@Override
	protected void initialize(){
		timer = new Timer();
		timer.reset();
		timer.start();
	}
	
	@Override
	protected void execute() {
		Robot.lifter.move(0.4);
	}
	
	@Override
	protected boolean isFinished() {
		return timer.get() > 0.20;
	}
	
	@Override
	protected void end() {
		Robot.lifter.stop();
	}
	
	@Override
	protected void interrupted() {
		Robot.lifter.stop();
	}
	
}
