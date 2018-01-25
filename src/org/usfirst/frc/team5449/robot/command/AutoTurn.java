package org.usfirst.frc.team5449.robot.command;

import java.lang.annotation.Target;
import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import sensors.EncoderModule;

/**
 *
 */
public class AutoTurn extends Command {
	//TODO Parameters
	private double Kp = RobotMap.CHASSIS_TURNING_P;
	private double Kd = RobotMap.CHASSIS_TURNING_D;
	private double allowedError = RobotMap.CHASSIS_TURNING_ALLOWED_ERROR;
	private Timer timer;
	private double lastError;
	private double currError;
	private double lastTime;
	private double target; //one use only; left is negative right is positive
	private double angleTarget;
	
    public AutoTurn(double target) {
        requires(Robot.chassis);
        this.target = target;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    	timer.reset();
    	lastTime = 0;
    	currError = target;
    	lastError = 0;
    	//TODO angleTarget = target+EncoderModule.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dt = timer.get() - lastTime;
    	//TODO currError = angleTarget-EncoderModule.getAngle();
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
        return true;
    	//TODO return (Math.abs(angleTarget-EncoderModule.getAngle())<allowedError);
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
