package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.Vector2d;

/**
 *
 */
public class DriveTo extends Command {
	private double TargetX,TargetY;
	private double X,Y;
    Vector2d t = new Vector2d(0,0);
    double p,theta;
	private boolean Stop = true;
    
	public DriveTo(double TargetX,double TargetY) {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        this.TargetX = TargetX;
        this.TargetY = TargetY;
        this.Stop = true;
    }
	
	public DriveTo(double TargetX,double TargetY,boolean Stop)
	{
		requires(Robot.chassis);
		this.TargetX = TargetX;
		this.TargetY = TargetY;
		this.Stop = Stop;
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	VectorUpdate();
    }

    
    private void VectorUpdate(){
    	
    	X = Robot.encodermodule.getX();
    	Y = Robot.encodermodule.getY();
    	t.x = TargetX - X;
    	t.y = TargetY - Y;
    	theta = Math.atan2(t.y, t.x);
    	p = t.magnitude();
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	VectorUpdate();
    	//TODO
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (p <= RobotMap.CHASSIS_MAX_PASSING_ERROR);
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (this.Stop){
    		//Robot.chassis.stop();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.chassis.stop();
    }
}
