package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class LiftDown extends Command{
	private Timer timeout;
	private int start_status;
	
	private final double TIMEOUT = 4.0;//unit:sec
	//WARNING: timeout has NOT been tested 
	
	public LiftDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	start_status = Robot.lifter.getStatus();
    	timeout = new Timer();
    	timeout.reset();
    	timeout.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.lifter.getStatus() > 0){
    		Robot.lifter.move_down();
    	}else if (Robot.lifter.getStatus() == 0){
    		Robot.lifter.stop();
    	}else{
    		Robot.lifter.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.lifter.is_down()||(timeout.get() >= TIMEOUT));
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (!(timeout.get() >= TIMEOUT)){
    	Robot.lifter.setStatus(0);
    	}else{
    		this.interrupted();
    	}
    	Robot.lifter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lifter.setStatus(start_status);
    }
	

}
