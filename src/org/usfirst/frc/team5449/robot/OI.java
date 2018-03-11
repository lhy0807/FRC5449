package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.Climb;
import org.usfirst.frc.team5449.robot.command.ClimbStop;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeIn_Remote;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.Intake_Release;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.LifterToMid2;
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

	public JoystickButton Climber_btn = new JoystickButton(stick1,7);
	
	public JoystickButton Holder_release = new JoystickButton(stick2,1);
	public JoystickButton Intake_stop = new JoystickButton(stick2,2);
	public JoystickButton SwitchReady = new JoystickButton(stick2,6);
	public JoystickButton Lifter_UP = new JoystickButton(stick2,7);
	public JoystickButton Lifter_MID2 = new JoystickButton(stick2,8);
	public JoystickButton Lifter_MID = new JoystickButton(stick2,10);
	public JoystickButton Lifter_DOWN = new JoystickButton(stick2,12);
	public JoystickButton Intake_out = new JoystickButton(stick2,5);
	public JoystickButton Intake_in = new JoystickButton(stick2,3);
	public JoystickButton IntakeReady = new JoystickButton(stick2,4);
	public JoystickButton Intake_Release_cube = new JoystickButton(stick2,11);
	
	public OI(){
		
		//joystick1
		//
		
		//button1
		//joystick2
		
		//button2
		SwitchReady.whenPressed(new org.usfirst.frc.team5449.robot.commandGroup.SwitchReady());
		Holder_release.whenPressed(new Release_Cube());
		IntakeReady.whenPressed(new org.usfirst.frc.team5449.robot.commandGroup.IntakeReady());
		Lifter_DOWN.whenPressed(new LifterToDown());
		Lifter_MID.whenPressed(new LifterToMid());
		Lifter_MID2.whenPressed(new LifterToMid2());
		Lifter_UP.whenPressed(new LifterToUp());
		Intake_in.whenPressed(new IntakeIn_Remote());
		Intake_out.whenPressed(new IntakeOut());
		Intake_stop.whenPressed(new IntakeStop());
		Intake_Release_cube.whenPressed(new Intake_Release());
	}
	
}
	
	

