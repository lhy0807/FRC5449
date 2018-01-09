package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Release_Cube extends Command{
	
	private Timer timer = new Timer();
	
	Release_Cube(){
		requires(Robot.holder);
	}
	// Called just before this Command runs the first time
    protected void initialize() {
    	//TODO set whatever (idk)
    	timer.reset();
    	timer.start();
    	Robot.holder.Push();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer.get() > RobotMap.HOLDER_DELAY);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.holder.Back();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
