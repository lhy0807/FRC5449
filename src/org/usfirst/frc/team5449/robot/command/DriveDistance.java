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
	private double Kp = 0.40;
	private double Kd = 3;
	private double AngleKp = 0.03;
	private double allowedError = 0.05;
	//system
	private Timer timer = new Timer();
	private double targetDistance;
	private final double drivingAngle;
	private double lastError;
	private double currError;
	private double lastTime;
	private double angle_error_last = 0;
	private double A_output_last = 0;
	
    public DriveDistance(double distance) {
    	requires(Robot.chassis);
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
    	currError = targetDistance - calcDis((Robot.chassis.get()[0]+Robot.chassis.get()[1])*0.5);
    	double dt = timer.get() - lastTime;
    	//pd&p
    	double varP = Kp*(currError);
    	double varD = Kd*(currError - lastError);
    	double angleP = AngleKp*(angleError);
    	if (Math.abs(angleError - angle_error_last) > 5){
    		angleP = A_output_last;
    	}
    	
    	double output = varP + varD;
    	output = range2(output,0.1,1);
    	SmartDashboard.putNumber("ERROR", currError);
    	SmartDashboard.putNumber("ERROR_DOT", currError - lastError);
    	SmartDashboard.putNumber("Angle_error", angleError);
    	Robot.chassis.arcade_drive(output,-angleP);//TODO 
    	A_output_last = angleP;
    	//retro
    	lastTime = timer.get();
    	angle_error_last = angleError;
    	lastError = currError;
    	currError = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	currError = targetDistance - calcDis((Robot.chassis.get()[0]+Robot.chassis.get()[1])*0.5);
    	return (Math.abs(currError)<=allowedError) || timer.get() >5;
    	
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
    	return encoderValue * 0.0003090;
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