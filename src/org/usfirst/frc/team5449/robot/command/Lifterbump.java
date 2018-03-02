package org.usfirst.frc.team5449.robot.command;


import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Lifterbump extends Command{
	public Lifterbump(){
		requires(Robot.lifter);
		this.time = 0.3;
	}
	
	public Lifterbump(double time){
		requires(Robot.lifter);
		this.time = time;
	}
	
	
	private Timer timer;
	private double time = 0.3;
	@Override
	protected void initialize(){
		timer = new Timer();
		timer.reset();
		timer.start();
	}
	
	@Override
	protected void execute() {
		Robot.lifter.move(0.40);
		
	}
	
	@Override
	protected boolean isFinished() {
		return timer.get() > this.time;
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
