package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;

/**
 *
 */
public class LifterToDown extends Command {
	
	private double error[] = {0,0};//{error,prev_error}
	private double Kp = RobotMap.LIFTER_KP;
	private double Kd = RobotMap.LIFTER_KD;
	private double last_time;
	private Timer timer = new Timer();
	
    public LifterToDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.reset();
    	timer.start();
    	last_time = timer.get();
    	error[0] = 0;
    	error[1] = RobotMap.LIFTER_DOWN_POSE - Robot.lifter.get_position();
    }
   
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double P_output,D_output,dt,output;
    	double balance_output;
    	error[0] = RobotMap.LIFTER_DOWN_POSE - Robot.lifter.get_position();
    	P_output = error[0] * Kp;
    	dt = timer.get() - last_time;
    	D_output = Kd * (error[0] - error[1])/dt;
    	error[1] = error[0];
    	last_time = timer.get();
    	
    	output = P_output + D_output;
    	output = range2(output,RobotMap.LIFTER_MINIUM_POWER,RobotMap.LIFTER_MAXIUM_POWER);
    	balance_output = RobotMap.LIFTER_BALANCE_KP * (Robot.lifter.get_position2()[0] - Robot.lifter.get_position2()[1]);
    	Robot.lifter.move(output * 0.15,balance_output);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean p1 =  Math.abs(RobotMap.LIFTER_DOWN_POSE - Robot.lifter.get_position()) < RobotMap.LIFTER_MAXIUM_PASSING_ERROR;
        boolean p2 = false;
        boolean p3 = Robot.intake.isIn();
        
        return p1 || p2 || p3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lifter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
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
