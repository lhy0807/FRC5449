package org.usfirst.frc.team5449.robot.command;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Delay extends Command{
	public Delay(double sec){
		this.waitsec = sec;
	}
	private double waitsec;
	private Timer timer;
	@Override
	protected void initialize(){
		timer = new Timer();
		timer.reset();
		timer.start();
	}
	@Override
	protected boolean isFinished() {
		return timer.get() > this.waitsec;
	}
}
