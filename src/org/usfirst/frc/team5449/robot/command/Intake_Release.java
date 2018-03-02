package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Intake_Release extends Command {
	private Timer t1 = new Timer();
    public Intake_Release() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
        requires(Robot.holder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO set whatever (idk)
    	t1.reset();
    	t1.start();
    	Robot.intake.Out();//Motor
    	Robot.intake.Open();//Cylinder
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (t1.get() > 0.4){
    		Robot.holder.Push();
    	}
    	if (t1.get() > 0.55){
    		Robot.intake.Close();
    	}
    	if (t1.get() > 1.4){
    		Robot.intake.Open();
    		Robot.intake.In2();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return t1.get() > 1.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.Stop();
    	Robot.holder.Back();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}