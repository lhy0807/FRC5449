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
	public double getJoystickX(){
		return stick1.getX();
	}	
	public double getJoystickY(){
		return stick1.getY();
	}	
	public double getJoystickZ(){
		return stick1.getZ();
	}
	public double getGyroZ(){
		return gyro.getAngle();
	}
	
	
	
}
