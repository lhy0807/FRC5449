package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import sensors.ProximitySwitch;

public class Lifter extends Subsystem{
	
	ProximitySwitch downswitch,midswitch,upswitch;
	private double power = 0.5;
	TalonSRX Liftmotor1,Liftmotor2;
	private int current_status = 0;//0 for down,1 for mid,2 for up
	
	public void Lifter(){
		downswitch = new ProximitySwitch(RobotMap.DOWN_PROXIMITY_SENSOR_PORT);
		midswitch = new ProximitySwitch(RobotMap.DOWN_PROXIMITY_SENSOR_PORT);
		upswitch = new ProximitySwitch(RobotMap.DOWN_PROXIMITY_SENSOR_PORT);
	}
	
	public void setPower(double power){
		this.power = power;
	}
	
	//moves lifter
	//TODO
	public void move_up(){
		
	}
	
	public void move_down(){
		
	}
	
	public void stop(){
		
	}
	
	//change status
	public void setStatus(int Status){
		if (Status >=0 && Status <=2){
			this.current_status = Status;
		}
	}
	
	//read sensors
	public boolean is_down(){
		return downswitch.get();
	}
	
	public boolean is_mid(){
		return midswitch.get();
	}
	
	public boolean is_up(){
		return upswitch.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
