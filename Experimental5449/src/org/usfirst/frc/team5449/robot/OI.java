package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
/**
 * OI Class: 
 * Define sensors and controls systems here
 * (this also serves as RobotMap so there is no need for a separate class)
 * */
import edu.wpi.first.wpilibj.command.Command;
public class OI {
	//RoboMap
	public static final int leftfront = 2;
	public static final int leftrear = 3;
	public static final int rightfront = 1;
	public static final int rightrear = 4;
	private static final int gyrochannel = 1;
	
	//Sensors
	AnalogGyro gyro1 = new AnalogGyro(gyrochannel);
	Joystick stick1 = new Joystick(1);
	JoystickButton button1 = new JoystickButton(stick1, 1);
	
	//
	
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
		return gyro1.getAngle();
	}
	/*public Command getAutonomousCommand(){
		//TODO return new (COMMANDGROUP)
		return null;//TODO change null
	}*/
	
}
