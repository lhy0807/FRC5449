package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeIn2 extends Command {

	private Timer timer1 = new Timer();
    public IntakeIn2() {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer1.reset();
    	timer1.start();
    	//TODO set whatever (idk)
    	Robot.intake.In();//Motor
    	Robot.intake.Open();//Cylinder
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (this.timer1.get() < 0.3){
    		Robot.intake.LeftIn();
    		Robot.intake.Close();
    	}else{
    		Robot.intake.In();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timer1.get() > 0.5);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
