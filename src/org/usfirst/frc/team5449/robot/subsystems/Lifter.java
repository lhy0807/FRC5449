package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.Lifter_Remote;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import sensors.ProximitySwitch;

public class Lifter extends Subsystem{
	TalonSRX Liftmotor_L,Liftmotor_R;
	private int current_status = 0;//0 for down,1 for mid,2 for up
	private Encoder lifter_encoder_l,lifter_encoder_r;
	public Lifter(){
		Liftmotor_L = new TalonSRX(RobotMap.LIFTER_LEFT_MOTOR_PORT);
		Liftmotor_R = new TalonSRX(RobotMap.LIFTER_RIGHT_MOTOR_PORT);	
		Liftmotor_R.setInverted(true);
		lifter_encoder_l = new Encoder(RobotMap.LIFTER_ENCODER_LEFT_PORT_A,RobotMap.LIFTER_ENCODER_LEFT_PORT_B);
		lifter_encoder_r = new Encoder(RobotMap.LIFTER_ENCODER_RIGHT_PORT_A,RobotMap.LIFTER_ENCODER_RIGHT_PORT_B);
		this.lifter_encoder_l.setReverseDirection(true);
	}
	
	
	//moves lifter
	public void move(double Power){
		Liftmotor_L.set(ControlMode.PercentOutput,Power);
		Liftmotor_R.set(ControlMode.PercentOutput,Power);
	}
	
	public void move(double Power,double delta_power){
		
		
		Liftmotor_L.set(ControlMode.PercentOutput,range(Power - delta_power,-RobotMap.LIFTER_MAXIUM_POWER,RobotMap.LIFTER_MAXIUM_POWER));
		Liftmotor_R.set(ControlMode.PercentOutput,range(Power + delta_power,-RobotMap.LIFTER_MAXIUM_POWER,RobotMap.LIFTER_MAXIUM_POWER));
		
		
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
	 
	public void stop(){
		Liftmotor_L.set(ControlMode.PercentOutput,0);
		Liftmotor_R.set(ControlMode.PercentOutput,0);
		
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
	public int[] get_position2(){
		int[] val = {lifter_encoder_l.get(),lifter_encoder_r.get()};
		return val;
	}
	
	public int get_position(){
		int val = (int)(lifter_encoder_l.get() + lifter_encoder_r.get()) / 2;
		return val;
	}
	//TODO
	public boolean is_down(){
		return (Math.abs(this.get_position() - RobotMap.LIFTER_DOWN_POSE) < RobotMap.LIFTER_MID_POSE * 0.1);
	}
	
	public void ResetEncoders(){
		this.lifter_encoder_l.reset();
		this.lifter_encoder_r.reset();
	}
	
	
	@Override
	protected void initDefaultCommand() {	
		this.setDefaultCommand(new Lifter_Remote());
	}
	

}
