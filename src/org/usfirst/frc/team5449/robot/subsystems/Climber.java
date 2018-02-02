package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.Climb_remote;
import org.usfirst.frc.team5449.robot.command.Lifter_Remote;
import org.usfirst.frc.team5449.robot.command.Move;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{
	
	private TalonSRX ClimberMotor1,ClimberMotor2;
	//parameters
	private double power = RobotMap.CLIMBER_POWER;
	
	public Climber(){
		ClimberMotor1 = new TalonSRX(RobotMap.CLIMBER_MOTOR_1_PORT);
		ClimberMotor2 = new TalonSRX(RobotMap.CLIMBER_MOTOR_2_PORT);
		ClimberMotor2.setInverted(true);
	}
	
	public void move(double Power){
		ClimberMotor1.set(ControlMode.PercentOutput,Power);
		ClimberMotor2.set(ControlMode.PercentOutput,Power);
	}
	
	public void stop(){
		ClimberMotor1.set(ControlMode.PercentOutput,0);
		ClimberMotor2.set(ControlMode.PercentOutput,0);
	}
	
	
	//set power
	public void setPower(double power){
		this.power = power;
	}
	
	
	
	@Override
	protected void initDefaultCommand() {	
		setDefaultCommand(new Climb_remote());
	}

}
