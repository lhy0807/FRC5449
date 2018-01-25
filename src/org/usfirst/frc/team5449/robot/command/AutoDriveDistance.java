package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * USFUL ONLY IF WE USE WHEEL ENCODERS OR IMPLEMENT OTHER METHODS THAT ARE MORE COMPLEX
 * DO NOT USE THIS METHOD, USE THE MORE COMPLEX ONE; 
 */
public class AutoDriveDistance extends Command {
	
	double length;
	private double Kp = 0;
	private double Kd = 0;
	private double allowedError = 0;
	private Timer timer;
	private double lastError;
	private double currError;
	private double lastTime;
	@Deprecated 
    public AutoDriveDistance(double length) {
    	this.length = length;
        requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	timer.reset();
    	lastTime = 0;
    	currError = length;
    	lastError = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
