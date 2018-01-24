package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;

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
	public JoystickButton button1,button2,button3;
	
	public OI(){
		//sensors
		gyro = new AnalogGyro(RobotMap.GYRO_PORT);
		//joystick
		stick1 = new Joystick(0);
		//button
		button1 = new JoystickButton(stick1, 1);
		button2 = new JoystickButton(stick1, 2);
		button3 = new JoystickButton(stick1, 3);
		button1.whenPressed(new IntakeIn());
		button2.whenPressed(new IntakeOut());
		button3.whenPressed(new IntakeStop());
		
		
		
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
