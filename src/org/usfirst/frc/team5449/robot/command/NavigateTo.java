package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import pathfinding.Simulator;

/**
 *
 */
public class NavigateTo extends Command {

	Simulator simulator;
	double[] TargetPos;
    public NavigateTo(double[] TargetPos) {
    	requires(Robot.chassis);
    	this.TargetPos = TargetPos;
    	// Use requires() here to declare subsystem dependencies
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double[] CurrentPos = {Robot.encodermodule.getX(),Robot.encodermodule.getY()};
    	simulator = new Simulator(CurrentPos,TargetPos,1.000);
    	simulator.Simulate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] Position = {Robot.encodermodule.getX(),Robot.encodermodule.getY()};
    	double[] Force = this.simulator.getForce(Position);
    	double Target_heading = Math.atan2(Force[1], -Force[0]);
    	//TODO
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}