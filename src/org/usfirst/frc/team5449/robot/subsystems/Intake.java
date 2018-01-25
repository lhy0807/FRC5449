package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Intake extends Subsystem{
	//motors
	private TalonSRX left_intake;
	private TalonSRX right_intake;
	private TalonSRX mid_intake;
	private Solenoid left_solenoid,right_solenoid;
	private Compressor Compressor;
	
	
	//TODO - may need pneumatics
	
	public Intake(){
		left_intake = new TalonSRX(RobotMap.LEFT_INTAKE_PORT);
		right_intake = new TalonSRX(RobotMap.RIGHT_INTAKE_PORT);
		mid_intake = new TalonSRX(RobotMap.MID_INTAKE_PORT);
		left_solenoid = new Solenoid(RobotMap.PCM_PORT,RobotMap.INTAKE_LEFT_SOLENOID_PORT);
		right_solenoid = new Solenoid(RobotMap.PCM_PORT,RobotMap.INTAKE_RIGHT_SOLENOID_PORT);
		Compressor = new Compressor(RobotMap.PCM_PORT);
	}
	
	//parameters:
	private double IntakePower = 1;
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	
	public void CompressorOn(){
		this.Compressor.start();
	}
	
	public void CompressorOff(){
		this.Compressor.stop();
	}
	
	public void In(){
		left_intake.set(ControlMode.PercentOutput,-IntakePower);
		right_intake.set(ControlMode.PercentOutput,IntakePower);
		mid_intake.set(ControlMode.PercentOutput, IntakePower);
	}
	
	public void Out(){
		left_intake.set(ControlMode.PercentOutput,IntakePower);
		right_intake.set(ControlMode.PercentOutput,-IntakePower);
		mid_intake.set(ControlMode.PercentOutput, IntakePower);
	}
	
	public void Stop(){
		left_intake.set(ControlMode.PercentOutput,0);
		right_intake.set(ControlMode.PercentOutput,0);
		mid_intake.set(ControlMode.PercentOutput,0);
	}
	
	public void Open(){
		left_solenoid.set(true);
		right_solenoid.set(true);
	}
	
	public void Close(){
		left_solenoid.set(false);
		right_solenoid.set(false);
	}
	
	public void LeftIn(){
		left_intake.set(ControlMode.PercentOutput,-IntakePower);
		mid_intake.set(ControlMode.PercentOutput, IntakePower);
	}
	
	public void RightIn(){
		right_intake.set(ControlMode.PercentOutput,IntakePower);
		mid_intake.set(ControlMode.PercentOutput, IntakePower);
	}
	
}
