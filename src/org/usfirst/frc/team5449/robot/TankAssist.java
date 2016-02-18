package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;

public class TankAssist extends RobotAssist{
	
	public void shooter() {
		if(Tank.getRawButton(shooter_bot)) {
			if (shooter_status==false) {
				if (shooter_mode==true) {
					shooter_mode=false;
				}
				else {
					shooter_mode=true;
				}
			}
			shooter_status=true;
			}
	else {
		shooter_status=false;
	}
		if(shooter_mode==true) {
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
		intake_r.set(Tank.getRawAxis(intake_axis));
		intake_l.set(Tank.getRawAxis(intake_axis));
		if (Tank.getRawButton(intake_bot)) {
			intake_r.set(-1);
			intake_l.set(-1);
			Timer.delay(0.5);
			intake_r.set(0);
			intake_l.set(0);
		}
	}
	
	public void arm() {
		if(Tank.getRawButton(arm_up)) {
			arm_r.set(1);
			arm_l.set(1);
			Timer.delay(0.05);
		}
		else if(Tank.getRawAxis(arm_down)>0.5) {
			arm_r.set(-1);
			arm_l.set(-1);
			Timer.delay(0.05);
		}
		else {
			arm_r.set(0);
			arm_l.set(0);
			Timer.delay(0.05);
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

