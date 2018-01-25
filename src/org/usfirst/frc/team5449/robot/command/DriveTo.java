package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.Vector2d;
import sensors.Gyro;

/**
 * NOTE THAT THE ENDING ANGLE IS RANDOM
 */
public class DriveTo extends Command {
	private double TargetX,TargetY;
	private double currX,currY;
    Vector2d t = new Vector2d(0,0);
    double distance,theta;
	private boolean Stop = true;
	
	//PID
	private double Drive_P = 0;
	private double Drive_D = 0;
	private double Turn_P = 0;
	private double Turn_D = 0;
	//private double Turn_Scale = 1;
	
	//time & error
	private Timer timer;
	private double lastTime;
	private double lastError_distance;
	private double currError_distance;
	private double lastError_angle;
	private double currError_angle;
	 
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
    	timer = new Timer();
    	timer.start();
    	timer.reset();
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
    	double dt = timer.get() - lastTime;
    	currError_distance = distance;
    	currError_angle = theta - Gyro.getAngle();
    	if(currError_angle>=180){
    		currError_angle -= 360;
    	}else if(currError_angle<=-180){
    		currError_angle += 360;
    	}
    	double distanceVarP = Drive_P*(currError_distance);
    	double distanceVarD = Drive_D*(currError_distance - lastError_distance)/dt;
    	double angleVarP = Turn_P*(currError_angle);
    	double angleVarD = Turn_D*(currError_angle-lastError_angle)/dt;
    	
    	double distance_output = distanceVarP+distanceVarD;
    	double angle_output = angleVarP+angleVarD;
    	
    	double leftPower = distance_output + angle_output; 
    	double rightPower = distance_output - angle_output;
    	
    	double trim = Math.max(Math.max(Math.abs(leftPower),Math.abs(rightPower)),1);
    	leftPower /= trim;
    	rightPower /= trim; 
    	
    	Robot.chassis.tankStyle(leftPower, rightPower);
    	
    	lastTime = timer.get();
    	lastError_distance = currError_distance;
    	lastError_angle = currError_angle;
    	currError_distance = 0;
    	currError_angle = 0;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (distance <= RobotMap.CHASSIS_MAX_PASSING_ERROR);
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
    
    private void VectorUpdate(){
    	currX = Robot.encodermodule.getX();
    	currY = Robot.encodermodule.getY();
    	t.x = TargetX - currX;
    	t.y = TargetY - currY;
    	theta = Math.atan2(t.y, t.x)*180.0/Math.PI;
    	distance = t.magnitude();
    }
    
}
