package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;

public class TankAssist extends RobotAssist{
	
	public void shooter() {
		if (Shoot.getRawButton(shooter_reverse)) {
			shooter_r.set(0.2);
			shooter_l.set(-0.2);
			Timer.delay(0.05);
		}
		else if (Shoot.getRawButton(shooter_bot)) {
			shooter_r.set(-1);
			shooter_l.set(1);
			Timer.delay(0.05);
		}
		else {
			shooter_r.set(0);
			shooter_l.set(0);
		}
	}
	
	public void intake() {
		if (Shoot.getRawButton(outake_bot)) {
			intake_r.set(-1);
			intake_l.set(-1);
			Timer.delay(0.1);
		}
		if (Shoot.getRawButton(intake_bot)) {
			intake_r.set(1);
			intake_l.set(1);
			Timer.delay(0.1);
		}
		else {
			intake_r.set(0);
			intake_l.set(0);
		}
	}
	
	public void arm() {
		if(Shoot.getRawButton(arm_up)) {
			arm_r.set(1);
			arm_l.set(1);
		}
		else if(Shoot.getRawButton(arm_down)) {
			arm_r.set(-1);
			arm_l.set(-1);
		}
		else if(Shoot.getRawButton(arm_up_slow)) {
			arm_r.set(0.5);
			arm_l.set(0.5);
		}
		else if(Shoot.getRawButton(arm_down_slow)) {
			arm_r.set(-0.5);
			arm_l.set(-0.5);
		}
		else {
			arm_r.set(0);
			arm_l.set(0);
		}
		
	}
	
	public void go(){
		//PowerChanger
		if(Tank.getRawButton(PowerChangerBot)) {
			if (button_status==false) {
				if (TankPower==Tankspeed1) {
					TankPower=Tankspeed2;
					//Accelerate
					if (reverse_mode==false) {
					Accelerate();
					}
				}
				else {
					TankPower=Tankspeed1;
					//Decelerate
					if (reverse_mode==false) {
					Decelerate();
					}
				}
			}
			button_status=true;
			}
	else {
		button_status=false;
	}
		//reverse_mode changer
	if (Tank.getRawButton(reverse_mode_Bot)) {
		if (reverse_button_status==false) {
			if (reverse_mode==false) {
				reverse_mode=true;
			}
			else {
				reverse_mode=false;
			}
			reverse_button_status=true;
		}
	}
		else {
			reverse_button_status=false;
		}
	//select direction, either normal or reverse
	if (reverse_mode==false) {
		NormalDirection();		
	}
	else if (reverse_mode==true) {
		ReverseDirection();		
	}
   Timer.delay(0.005);		// wait for a motor update time
 }
}

