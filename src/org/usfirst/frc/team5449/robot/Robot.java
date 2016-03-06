 package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TankAssist {

    public void operatorControl() {    	
    	init();
        while (isOperatorControl() && isEnabled()) { 
        	EncoderTest();
        	intake();//intake module
        	shooter();//shooter module
        	go();//Tank module
        	arm();
        	double range = ultra.getRangeInches();
        	SmartDashboard.putNumber("ultra data", range);
        }
    }
    
    public void autonomous() {
    	init();
    	while (isAutonomous() && isEnabled()){
    		mot_l1.set(-0.25);
	    	mot_l2.set(-0.25);
	    	mot_r1.set(0.25);
	    	mot_r2.set(0.25);
    		Timer.delay(0.1);
    		
    		Enc_l.reset();
    		Enc_r.reset();	
        	pid_init();
        	
        	arm_l.set(0.31);
    		arm_r.set(0.31);
    		AutoPID(-900,-1000,-900,-1050);
    		arm_l.set(0);
    		arm_r.set(0);
    		pid_init();
    		AutoPID(-600,-1500,-600,-1500);
    		
    		shooter_r.set(-0.4);
			shooter_l.set(0.4);
			arm_l.set(-0.8);
    		arm_r.set(-0.8);
    		Timer.delay(0.65);
    		arm_l.set(0);
    		arm_r.set(0);
    		pid_init();
    		AutoPID(-400,-310,400,310);
    		
    		shooter_r.set(-0.8);
			shooter_l.set(0.8);
    		
    		pid_init();
    		Timer.delay(0.1);
    		AutoPID(1500,1700,1500,1700);
    		
    		shooter_r.set(-1);
			shooter_l.set(1);
    		
    		pid_init();
    		Timer.delay(0.1);
    		AutoPID(-400,-320,400,320);
    		pid_init();
    		AutoPID(-300,-100,-300,-100);
//    		mot_l1.set(0.1);
//	    	mot_l2.set(0.1);
//	    	mot_r1.set(-0.1);
//	    	mot_r2.set(-0.1);
    		
			intake_r.set(1);
			intake_l.set(1);
			Timer.delay(2);
			shooter_r.set(0);
			shooter_l.set(0);
			intake_r.set(0);
			intake_l.set(0);
			mot_l1.set(0);
	    	mot_l2.set(0);
	    	mot_r1.set(0);
	    	mot_r2.set(0);
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
