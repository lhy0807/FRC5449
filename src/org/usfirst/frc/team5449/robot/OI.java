package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.Intake_Release;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.LifterToUp;
import org.usfirst.frc.team5449.robot.command.Release_Cube;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * OI Class: 
 * Define sensors and controls systems here
 * Scheduler will search here
 * */
public class OI {
	
	//initiate
	public Joystick stick1 = new Joystick(0);
	public Joystick stick2 = new Joystick(1);

	
	public JoystickButton Holder_release = new JoystickButton(stick2,1);
	public JoystickButton Intake_release = new JoystickButton(stick2,2);
	public JoystickButton Lifter_UP = new JoystickButton(stick2,8);
	public JoystickButton Lifter_MID = new JoystickButton(stick2,10);
	public JoystickButton Lifter_DOWN = new JoystickButton(stick2,12);
	public JoystickButton Intake_out = new JoystickButton(stick2,5);
	public JoystickButton Intake_in = new JoystickButton(stick2,3);
	public JoystickButton Intake_stop = new JoystickButton(stick2,4);
	
	
	public OI(){
		
		//joystick1
		//
		//button1
		//joystick2
		
		//button2
		Holder_release.whenPressed(new Release_Cube());
		Intake_release.whenPressed(new Intake_Release());
		Lifter_DOWN.whenPressed(new LifterToDown());
		Lifter_MID.whenPressed(new LifterToMid());
		Lifter_UP.whenPressed(new LifterToUp());
		Intake_in.whenPressed(new IntakeIn());
		Intake_out.whenPressed(new IntakeOut());
		Intake_stop.whenPressed(new IntakeStop());
	}
	
}
	
	

