package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import triggers.LiftTriggerTest;

/**
 * OI Class: 
 * Define sensors and controls systems here
 * Scheduler will search here
 * */
public class OI {
	
	//initiate
	public Joystick stick1;
	public JoystickButton button1,button2,button3;
	
	public OI(){

		//joystick
		stick1 = new Joystick(0);
		//button
		button1 = new JoystickButton(stick1, 1);
		button2 = new JoystickButton(stick1, 2);
		button3 = new JoystickButton(stick1, 3);
		button1.whenPressed(new IntakeIn());
		button2.whenPressed(new IntakeOut());
		button3.whenPressed(new IntakeStop());
		

	}
	

	
	
}
