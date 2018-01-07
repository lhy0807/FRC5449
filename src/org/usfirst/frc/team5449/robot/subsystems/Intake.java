package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	//motors
	private TalonSRX left_intake;
	private TalonSRX right_intake;
	
	
	//TODO - may need pneumatics
	
	Intake(){
		left_intake = new TalonSRX(RobotMap.LEFT_INTAKE_PORT);
		right_intake = new TalonSRX(RobotMap.RIGHT_INTAKE_PORT);
	}
	
	//parameters:
	private double IntakePower = 1;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void In(){
		left_intake.set(ControlMode.PercentOutput,IntakePower);
		right_intake.set(ControlMode.PercentOutput,-IntakePower);
	}
	
	public void Out(){
		left_intake.set(ControlMode.PercentOutput,-IntakePower);
		right_intake.set(ControlMode.PercentOutput,IntakePower);
	}
	
	public void Stop(){
		left_intake.set(ControlMode.PercentOutput,0);
		right_intake.set(ControlMode.PercentOutput,0);
	}
}
