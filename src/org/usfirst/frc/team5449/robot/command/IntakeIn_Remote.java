package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeIn_Remote extends Command {
	
	private boolean is_holding = false;
	Timer timer = new Timer();
	double time = -1;
	private double timeout = 3.00;
    public IntakeIn_Remote() {
    	
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
    }
    
    public IntakeIn_Remote(double timeout) {
    	
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.intake);
        this.timeout = timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//TODO set whatever (idk)
    	is_holding = false;
    	time = -1;
    	timer.reset();
    	timer.start();
    	Robot.intake.In2();//Motor
    	Robot.intake.Close();//Cylinder
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!is_holding && Robot.holder.is_holding_block()){
    		time = timer.get();
    		is_holding = true;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > this.timeout || (Robot.holder.is_holding_block() && (timer.get() > time + 0.5));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.Stop();//Motor
    	//Robot.intake.Open();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}