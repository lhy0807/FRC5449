package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeIn2 extends Command {

    public IntakeIn2() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.Close();
    	//TODO set whatever (idk)
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    		Robot.intake.Close();

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
