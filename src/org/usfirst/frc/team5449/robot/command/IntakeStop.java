package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeStop extends Command {

    public IntakeStop() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO set whatever (idk)
    	Robot.intake.Stop();//Motor
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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