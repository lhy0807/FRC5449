package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import sensors.ProximitySwitch;

public class Lifter extends Subsystem{
	TalonSRX Liftmotor_L,Liftmotor_R;
	private int current_status = 0;//0 for down,1 for mid,2 for up
	private Encoder lifter_encoder_1;
	public Lifter(){
		Liftmotor_L = new TalonSRX(RobotMap.LIFTER_LEFT_MOTOR_PORT);
		Liftmotor_R = new TalonSRX(RobotMap.LIFTER_RIGHT_MOTOR_PORT);	
		Liftmotor_R.setInverted(true);
		lifter_encoder_1 = new Encoder(RobotMap.LIFTER_ENCODER_PORT_A,RobotMap.LIFTER_ENCODER_PORT_B);
	}
	
	
	//moves lifter
	//TODO
	public void move(double Power){
		Liftmotor_L.set(ControlMode.PercentOutput,Power);
		Liftmotor_R.set(ControlMode.PercentOutput,Power);
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
	public long get_position(){
		return lifter_encoder_1.get();
	}
	//TODO
	public boolean is_down(){
		return false;
	}
	
	@Override
	protected void initDefaultCommand() {		
	}
}
