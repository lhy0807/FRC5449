package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import sensors.Gyro;

/**
 *
 */
public class DriveDistance extends Command {
	//configure
	private double Kp = 0;
	private double Kd = 0;
	private double AngleKp = 0;
	private double allowedError = 5;
	//system
	private Timer timer = new Timer();
	private double targetDistance;
	private final double drivingAngle;
	private double lastError;
	private double currError;
	private double lastTime;
	
	
    public DriveDistance(double distance) {
		this.targetDistance = distance;
		drivingAngle = Gyro.getAngle();
	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	lastTime = -0.02;
    	currError = targetDistance - calcDis((Robot.chassis.get()[0]+Robot.chassis.get()[1])/2);
    	lastError = targetDistance;
    }
   
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleError = drivingAngle - Gyro.getAngle();
    	if(angleError>180){
    		angleError -= 360;
    	}else if(angleError<-180){
    		angleError += 360;
    	}
    	currError = targetDistance - calcDis((Robot.chassis.get()[0]+Robot.chassis.get()[1])/2);
    	double dt = timer.get() - lastTime;
    	//pd&p
    	double varP = Kp*(currError);
    	double varD = Kd*(currError - lastError);
    	double angleP = AngleKp*(angleError);
    	
    	double output = varP + varD;
    	output = range2(output,0,1);
    	
    	Robot.chassis.tankStyle(output,-angleP);//TODO 
    	
    	//retro
    	lastTime = timer.get();
    	lastError = currError;
    	currError = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currError = targetDistance - calcDis((Robot.chassis.get()[0]+Robot.chassis.get()[1])/2);
    	return (Math.abs(currError)<=allowedError);
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    /**cm*/
    private double calcDis(double encoderValue){
    	return encoderValue/5000*10.16;
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
    
    
}