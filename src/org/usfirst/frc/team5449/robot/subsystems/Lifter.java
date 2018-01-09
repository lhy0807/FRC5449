package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import sensors.ProximitySwitch;

public class Lifter extends Subsystem{
	private double power = 0.5;
	TalonSRX Liftmotor1,Liftmotor2;
	private int current_status = 0;//0 for down,1 for mid,2 for up
	private Encoder lifter_encoder_1;
	public Lifter(){
		lifter_encoder_1 = new Encoder(RobotMap.LIFTER_ENCODER_PORT_A,RobotMap.LIFTER_ENCODER_PORT_B);
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
	
	public int getStatus(){
		return this.current_status;
	}
	
	//read sensors
	public long get_position(){
		return lifter_encoder_1.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
