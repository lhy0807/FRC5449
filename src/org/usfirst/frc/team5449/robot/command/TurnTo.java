package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import sensors.Gyro;

/**
 *
 */
public class TurnTo extends Command {
	//TODO Parameters
	private double Kp = RobotMap.CHASSIS_TURNING_P;
	private double Kd = RobotMap.CHASSIS_TURNING_D;
	private double allowedError = RobotMap.CHASSIS_TURNING_ALLOWED_ERROR;
	private Timer timer;
	private double lastError;
	private double currError;
	private double lastTime;
	private double angleTarget;
	private double lastoutput;
	
    public TurnTo(double angleTarget) {
        requires(Robot.chassis);
        this.angleTarget = angleTarget;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	lastTime = -0.02;
    	currError = angleTarget - Gyro.getAngle();
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
    	double Heading2 = Math.toRadians((Gyro.getAngle() - Robot.FirstHeading));
    	currError = angleTarget - Math.toDegrees(Math.atan2(Math.sin(Heading2), Math.cos(Heading2)));
    	
    	if(currError>180){
    		currError -= 360;
    	}else if(currError<-180){
    		currError += 360;
    	}
    	double varP = Kp*(currError);
    	double varD = Kd*(currError - lastError);
    	double output = varP + varD;
    	output = range2(output,0.25,1);
    	if (currError - lastError > 10){
    		output = lastoutput;
    	}
    	
    	
    	lastoutput = output;
    	Robot.chassis.arcade_drive(0, -output);
    	
    	SmartDashboard.putNumber("Xdot", (currError - lastError));
    	//Retro
    	
    	lastTime = timer.get();
    	lastError = currError;
    	currError = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double Heading2 = Math.toRadians((Gyro.getAngle() - Robot.FirstHeading));
    	currError = angleTarget - Math.toDegrees(Math.atan2(Math.sin(Heading2), Math.cos(Heading2)));
    	
    	if(currError>180){
    		currError -= 360;
    	}else if(currError<-180){
    		currError += 360;
    	}
    	SmartDashboard.putNumber("error", Math.abs(currError));
    	
        return (Math.abs(currError)<allowedError && Math.abs(currError - lastError) < 0.1);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.arcade_drive(0, 0);
    }
    private double range2(double val,double min,double max){
    	max = Math.abs(max);
    	min = Math.abs(min);
    	if (Math.abs(val)<min){
    		return Math.signum(val) * min;
    	} else if (Math.abs(val) > max){
    		return Math.signum(val) * max;
    	}else{
    		return Math.signum(val) * Math.abs(val);
    	}
    	
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//TODO override Joystick output 
    	
    }
	 private double range(double val,double min,double max){
	    	if (val < min){
	    		return min;
	    	}else if (val > max){
	    		return max;
	    	}else{
	    		return val;
	    	}
	    } 
}
