package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import sensors.Gyro;

/**
 *
 */
public class Turn extends Command {
	//TODO Parameters
	private double Kp = RobotMap.CHASSIS_TURNING_P;
	private double Kd = RobotMap.CHASSIS_TURNING_D;
	private double allowedError = RobotMap.CHASSIS_TURNING_ALLOWED_ERROR;
	private Timer timer;
	private double lastError;
	private double currError;
	private double lastTime;
	private double angleTarget;
	
    public Turn(double angleTarget) {
        requires(Robot.chassis);
        this.angleTarget = angleTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	timer.reset();
    	lastTime = 0;
    	//TODO currError = angleTarget - getAngle();
    	if(currError>180){
    		currError -= 360;
    	}else if(currError<-180){
    		currError += 360;
    	}
    	lastError = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dt = timer.get() - lastTime;
    	currError = angleTarget - Gyro.getAngle();
    	if(currError>180){
    		currError -= 360;
    	}else if(currError<-180){
    		currError += 360;
    	}
    	lastError = 0;
    	double varP = Kp*(currError);
    	double varD = Kd*(currError - lastError)/dt;
    	double output = varP + varD;
    	if(output>=1){
    		output = 1.0;
    	}else if(output<=-1){
    		output = -1.0;
    	}
    	Robot.chassis.tankStyle(output, -output);
    	//Retro
    	lastTime = timer.get();
    	lastError = currError;
    	currError = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(angleTarget-Gyro.getAngle())<allowedError);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.tankStyle(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO override Joystick output 
    	
    }
}
