package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TankAssist {

    public void operatorControl() {    	
    	init();
        while (isOperatorControl() && isEnabled()) { 
        	pid_init();
        	intake();//intake module
        	shooter();//shooter module
        	go();//Tank module
        	arm();
        	double range = ultra.getRangeInches();
        	SmartDashboard.putNumber("ultra data", range);
        	SmartDashboard.putNumber("arm_l_distance", Enc_arm_l.getDistance());
        	SmartDashboard.putNumber("arm_r_distance", Enc_arm_r.getDistance());
        }
    }
    
    public void autonomous() {
    	init();
    	while (isAutonomous() && isEnabled()){
    		Timer.delay(0.1);
    		Enc_l.reset();
    		Enc_r.reset();		
        	pid_init();
    		AutoPID(-1000,-1000,-900,-1000);
    		
    		pid_init();
    		AutoPID(-600,-1200,-600,-1200);
    		
    		shooter_r.set(-0.4);
			shooter_l.set(0.4);
    		
    		Timer.delay(0.2);
    		
    		pid_init();
    		AutoPID(-400,-300,400,300);
    		
    		shooter_r.set(-0.8);
			shooter_l.set(0.8);
    		
    		pid_init();
    		Timer.delay(0.1);
    		AutoPID(1500,1800,1500,1800);
    		
    		shooter_r.set(-1);
			shooter_l.set(1);
    		
    		pid_init();
    		Timer.delay(0.1);
    		AutoPID(-400,-260,400,260);
    		
    		pid_init();
    		
    		
			intake_r.set(1);
			intake_l.set(1);
			Timer.delay(2);
			shooter_r.set(0);
			shooter_l.set(0);
			intake_r.set(0);
			intake_l.set(0);
    		break;
    	}
    	Enc_l.reset();
		Enc_r.reset();
    }
    
    public void test() {
    	init();
    	while (isTest() && isEnabled()){
    		arm_l.set(0.1);
    		arm_r.set(0.1);
    		Timer.delay(1);
    		arm_l.set(0);
    		arm_r.set(0);
    	}
    }

}
