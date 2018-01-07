package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * OI Class: 
 * Define sensors and controls systems here
 * Scheduler will search here
 * */
public class OI {
	
	//initiate
	public AnalogGyro gyro;
	public Joystick stick1;
	public JoystickButton button1;
	
	public OI(){
		//sensors
		gyro = new AnalogGyro(RobotMap.GYRO_PORT);
		//joystick
		stick1 = new Joystick(1);
		//button
		button1 = new JoystickButton(stick1, 1);
		//button function Map
		//button1.whileHeld(new whatever_Command);
		
	}
	
	//Methods
	public double getJoystickX(Joystick stick){
		return stick.getX();
	}	
	public double getJoystickY(Joystick stick){
		return stick.getY();
	}	
	public double getJoystickZ(Joystick stick){
		return stick.getZ();
	}
	public double getGyroZ(){
		return gyro.getAngle();
	}
	
	
	
}
