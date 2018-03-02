package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import sensors.Gyro;

/**
 * NOTE THAT THE ENDING ANGLE IS RANDOM
 */
public class DriveTo extends Command {
	private double TargetX,TargetY;
	private double currX,currY;
	double[] t = {0,0};	
	
    double distance,theta;
	private boolean Stop = true;
	
	//PID
	private double Drive_P = 0.5;
	private double Drive_D = 3.5;
	private double Turn_P = 0.024;
	private double Turn_D = 0.24;
	//private double Turn_Scale = 1;
	
	//time & error
	private Timer timer;
	private double lastTime;
	private double lastError_distance;
	private double currError_distance;
	private double lastError_angle;
	private double currError_angle;
	 
	public DriveTo(double[] Target) {
    	// Use requires() here to declare subsystem dependencies
        //requires(Robot.chassis);
        this.TargetX = Target[0];
        this.TargetY = Target[1];
        this.Stop = true;
    }
	public DriveTo(double[] Target,boolean Stop)
	{
		//requires(Robot.chassis);
		this.TargetX = Target[0];
		this.TargetY = Target[1];
		this.Stop = Stop;
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	double Angle = Gyro.getAngle();
    	VectorUpdate(Angle);
    	timer = new Timer();
    	timer.reset();
    	timer.start();
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
    	
    	double Angle = Gyro.getAngle();
    	VectorUpdate(Angle);
    	double dt = timer.get() - lastTime;
    	currError_distance = distance;
    	currError_angle = theta - Angle;
    	
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
    	
    	distance_output = range(distance_output,-0.7,0.7);
    	if (!this.Stop){
    		distance_output = 0.5;
    	}
    	
    	
    	angle_output = range(angle_output,-0.6,0.6);
    	
    	Robot.chassis.arcade_drive(distance_output, -angle_output);
    	
    	SmartDashboard.putNumber("t.X", this.t[0]);
    	SmartDashboard.putNumber("t.Y", this.t[1]);
    	SmartDashboard.putNumber("theta", this.theta);
    	SmartDashboard.putNumber("distance_output", distance_output);
    	SmartDashboard.putNumber("theangle_output", angle_output);
    	
    	
    	
    	
    	lastTime = timer.get();
    	lastError_distance = currError_distance;
    	lastError_angle = currError_angle;
    	currError_distance = 0;
    	currError_angle = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (distance <= RobotMap.CHASSIS_MAX_PASSING_ERROR* 2.0) || timer.get() > 10;
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (this.Stop){
    		Robot.chassis.stop();;
    	}else{
    		//TODO
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.chassis.stop();
    }
    
    private void VectorUpdate(double Angle){
    	currX = Robot.e1.get(Math.toRadians(Angle))[0] * 0.001;
    	currY = Robot.e1.get(Math.toRadians(Angle))[1] * 0.001;
    	
    	t[0] = TargetX - currX;
    	t[1] = TargetY - currY;
    	
    	theta = Math.toDegrees(-Math.atan2(t[0], t[1]));
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
