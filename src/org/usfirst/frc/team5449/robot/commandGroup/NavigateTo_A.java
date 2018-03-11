package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.DriveTo;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pathfinding.Simulator;
import sensors.Gyro;

/**
 * NOTE THAT THE ENDING ANGLE IS RANDOM
 */
public class NavigateTo_A extends CommandGroup {
	double[] t = {0,0};	
	private double[] TargetPos = {0,0};
	private double[] Position = {0,0};
	
    double distance,theta;
	private boolean Stop = true;
	
	//Simulator 
	private Simulator simulator;
	private boolean is_reachable = false;
	
	//time & error
	private Timer timer;
	@Deprecated
	public NavigateTo_A(double[] TargetPos) {
    	// Use requires() here to declare subsystem dependencies
		this.TargetPos = TargetPos;
        this.Stop = true;
    }
	@Deprecated
	public NavigateTo_A(double[] TargetPos,boolean Stop)
	{
		this.TargetPos = TargetPos;
		this.Stop = Stop;
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	Position[0] = Robot.e1.get(Math.toRadians(Gyro.getAngle()))[0] * 0.001d;
    	Position[1] = Robot.e1.get(Math.toRadians(Gyro.getAngle()))[1] * 0.001d;
    	
    	simulator = new Simulator(Position,this.TargetPos,2.00d);	
    	SmartDashboard.putString("Navigate To", "COMMAND WORKING");
    	double[][] Waypoints = this.simulator.Simulate2();
    	SmartDashboard.putString("Navigate To", "Calculation done");
    	for (int i = 0; i < Waypoints.length;i++){
    		double[] val2 = {0,0};
    		val2[0] = Waypoints[i][0];
    		val2[1] = Waypoints[i][1];
    		if (i == Waypoints.length -1){
    			addSequential(new DriveTo(val2,true));
    		}else{
    			addSequential(new DriveTo(val2,false));
    		}
    	}
    }  
}
