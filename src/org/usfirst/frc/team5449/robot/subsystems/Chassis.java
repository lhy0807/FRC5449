package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.Drive;
import org.usfirst.frc.team5449.robot.command.Move;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Chassis extends Subsystem {
	
	//initiate 
	private TalonSRX LeftMotorA;
	private TalonSRX LeftMotorB;
	private TalonSRX LeftMotorC;
	private TalonSRX RightMotorA;
	private TalonSRX RightMotorB;
	private TalonSRX RightMotorC;
	
	public Chassis(){
		//TODO set ports CHANGE IT TO IMPLEMENT ROBOMAP IF PUT THIS IN USE
		LeftMotorA = new TalonSRX(RobotMap.LEFT_FRONT_MOTOR_PORT);
		LeftMotorB = new TalonSRX(RobotMap.LEFT_MID_MOTOR_PORT);
		LeftMotorC = new TalonSRX(RobotMap.LEFT_REAR_MOTOR_PORT);
		RightMotorA = new TalonSRX(RobotMap.RIGHT_FRONT_MOTOR_PORT);
		RightMotorB = new TalonSRX(RobotMap.RIGHT_MID_MOTOR_PORT);
		RightMotorC = new TalonSRX(RobotMap.RIGHT_REAR_MOTOR_PORT);
		//reverse right side
		RightMotorA.setInverted(true);
    	RightMotorB.setInverted(true);
    	RightMotorC.setInverted(true);
		//slave
		LeftMotorB.set(ControlMode.Follower, LeftMotorA.getDeviceID());
		LeftMotorC.set(ControlMode.Follower, LeftMotorA.getDeviceID());
		RightMotorB.set(ControlMode.Follower, RightMotorA.getDeviceID());
		RightMotorC.set(ControlMode.Follower, RightMotorA.getDeviceID());
	}
	
	private void arcadeStyle(double power, double turn){
		double leftPower, rightPower;
		double Speed = stickScaling(power);
		double Rotate = stickScaling(turn);
		
		if(Speed>=0.0){
			if(Rotate>=0.0){
				leftPower = Math.max(Math.abs(Speed), Math.abs(Rotate));
				rightPower = Speed - Rotate;
			}else{
				leftPower = Speed + Rotate;
				rightPower = Math.max(Math.abs(Speed), Math.abs(Rotate));
			}
		}else{
			if(Rotate>=0.0){
				leftPower = Speed + Rotate;
				rightPower = -Math.max(Math.abs(Speed), Math.abs(Rotate));
			}else{
				leftPower = -Math.max(Math.abs(Speed), Math.abs(Rotate));
				rightPower = Speed - Rotate;
			}
		}
		LeftMotorA.set(ControlMode.PercentOutput, leftPower);
		RightMotorA.set(ControlMode.PercentOutput, rightPower);
	}
	
	public void tankStyle(double leftInput, double rightInput){
		double leftPower = stickScaling(leftInput);
		double rightPower = stickScaling(rightInput);
		LeftMotorA.set(ControlMode.PercentOutput, leftPower);
		RightMotorA.set(ControlMode.PercentOutput, rightPower);
	}
	
	
	private double stickScaling(double input){
		//TODO scale the stick for this chassis;
		//limit 0-1 => Deadzone => square input
		return input;
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Move());
    }
    
    @Deprecated
	public void setEncoder(TalonSRX testMotor){
		testMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10); //
		testMotor.setSensorPhase(false); //true to invert sensor???
		testMotor.getSelectedSensorPosition(0); //???
	}
    
    public void stop(){
    	LeftMotorA.set(ControlMode.PercentOutput,0);
    	LeftMotorB.set(ControlMode.PercentOutput, 0);
    	LeftMotorC.set(ControlMode.PercentOutput,0);
    	RightMotorA.set(ControlMode.PercentOutput,0);
    	RightMotorB.set(ControlMode.PercentOutput, 0);
    	RightMotorC.set(ControlMode.PercentOutput,0);
	}
    
}

