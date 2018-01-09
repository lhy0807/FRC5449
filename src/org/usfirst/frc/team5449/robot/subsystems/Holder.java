package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Holder extends Subsystem{
	
	Solenoid block_holder,s2;
	
	Holder(){
		block_holder = new Solenoid(RobotMap.HOLDER_PORT);
		s2 = new Solenoid(RobotMap.S2_PORT);
		
	}
	
	public void open(){
		
	} 
	
	public void close(){
		
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
