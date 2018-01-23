package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Holder extends Subsystem{
	
	Solenoid block_pusher_1,block_pusher_2;
	
	public Holder(){
		block_pusher_1 = new Solenoid(RobotMap.PCM_PORT,RobotMap.HOLDER_BLOCK_PUSHER1_PORT);
		block_pusher_2 = new Solenoid(RobotMap.PCM_PORT,RobotMap.HOLDER_BLOCK_PUSHER2_PORT);
		
	}
	
	public void Back(){
		block_pusher_1.set(false);
		block_pusher_2.set(false);
	} 
	
	public void Push(){
		block_pusher_1.set(true);
		block_pusher_2.set(true);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
	

}
