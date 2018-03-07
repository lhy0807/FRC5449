package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pathfinding.Simulator;
import sensors.Gyro;

/**
 * NOTE THAT THE ENDING ANGLE IS RANDOM
 */
public class NavigateTo extends Command {
	double[] t = {0,0};	
	private double[] TargetPos = {0,0};
	private double[] Position = {0,0};
	
    double distance,theta;
	private boolean Stop = true;
	
	//PID
	private double Drive_P = 0.5;
	private double Drive_D = 3.5;
	private double Turn_P = 0.028;
	private double Turn_D = 0.24;
	//Simulator 
	private Simulator simulator;
	private boolean is_reachable = false;
	
	//time & error
	private Timer timer;
	private double lastTime;
	private double lastError_distance;
	private double currError_distance;
	private double lastError_angle;
	private double currError_angle;
	private double Angle_d;
	@Deprecated
	public NavigateTo(double[] TargetPos) {
    	// Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
		this.TargetPos = TargetPos;
        this.Stop = true;
    }
	@Deprecated
	public NavigateTo(double[] TargetPos,boolean Stop)
	{
		requires(Robot.chassis);
		this.TargetPos = TargetPos;
		this.Stop = Stop;
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	VectorUpdate();
    	SmartDashboard.putNumber("startposX", Position[0]);
    	SmartDashboard.putNumber("startposY", Position[1]);
    	simulator = new Simulator(this.Position,this.TargetPos,2.00d);
    	
    	timer = new Timer();
    	timer.reset();
    	timer.start();
    	SmartDashboard.putString("Navigate To", "COMMAND WORKING");
    	is_reachable = this.simulator.Simulate();
    	SmartDashboard.putString("Navigate To", "Calculation done");
    	
    	
    	lastTime = 0;
    	currError_distance = distance;
    
    	lastError_distance = 0;
    	currError_angle = theta - Gyro.getAngle();
    	if(currError_angle>180){
    		currError_angle -= 360;
    	}else if(currError_angle<-180){
    		currError_angle += 360;
    	}
    	lastError_angle = 0;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	VectorUpdate();
    	double[] Force = simulator.getForce(Position);
    	theta = -Math.toDegrees(Math.atan2(Force[0], Force[1]));
    	SmartDashboard.putNumber("theta", theta);
    	SmartDashboard.putNumber("ForceX", Force[0]);
    	SmartDashboard.putNumber("ForceY", Force[1]);
    	
    	double dt = timer.get() - lastTime;
    	currError_distance = distance;
    	currError_angle = theta - Angle_d;
    	
    	if(currError_angle>=180){
    		currError_angle -= 360;
    	}else if(currError_angle<=-180){
    		currError_angle += 360;
    	}
    	
    	
    	
    	double distanceVarP = Drive_P*(currError_distance);
    	double distanceVarD = Drive_D*(currError_distance - lastError_distance);
    	double angleVarP = Turn_P*(currError_angle);
    	double angleVarD = Turn_D*(currError_angle-lastError_angle);
    	
    	double distance_output = distanceVarP+distanceVarD;
    	double angle_output = angleVarP+angleVarD;
    	
    	distance_output = range(distance_output,0,0.4);
    	angle_output = range(angle_output,-0.5,0.5);
    	

    	SmartDashboard.putNumber("Angle_err", distance_output);
    	SmartDashboard.putNumber("DIS_Power", distance_output);
    	SmartDashboard.putNumber("Angle_Power",  -angle_output);
    	Robot.chassis.arcade_drive(distance_output, -angle_output);
    	
    	lastTime = timer.get();
    	lastError_distance = currError_distance;
    	lastError_angle = currError_angle;
    	currError_distance = 0;
    	currError_angle = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("dis",  -distance);
        return (distance <= RobotMap.CHASSIS_MAX_PASSING_ERROR * 1.5) || timer.get() > 60;
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putString("Navigate To", "Command Ends");
    	if (this.Stop){
    		Robot.chassis.stop();
    	}else{
    		//TODO
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.chassis.stop();
    }
    
    private void VectorUpdate(){
    	Angle_d = Gyro.getAngle();
    	this.Position[0] = Robot.e1.get(Math.toRadians(Angle_d))[0] * 0.001;
    	this.Position[1] = Robot.e1.get(Math.toRadians(Angle_d))[1] * 0.001;
    	t[0] = TargetPos[0] - Position[0];
    	t[1] = TargetPos[1] - Position[1];
    	distance = Math.hypot(t[0], t[1]);
    	
    }
	 private double range(double val,double min,double max){
	    	if (val < min){
	    		return min;
	    	}else if (val > max){
	    		return max;
	    	}else{
	    		return val;
	    	}
	    }
    
}
