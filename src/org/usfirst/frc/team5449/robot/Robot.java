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
        	Enc_reset();
        	double range = ultra.getRangeInches();
        	SmartDashboard.putNumber("ultra data", range);
        	SmartDashboard.putNumber("arm_l_distance", Enc_arm_l.getDistance());
        	SmartDashboard.putNumber("arm_r_distance", Enc_arm_r.getDistance());
        }
    }
    
    public void autonomous() {
    	init();
    	while (isAutonomous() && isEnabled()){
    		Enc_l.reset();
    		Enc_r.reset();
    		
        	pid_init();
        	Timer.delay(5);
    		AutoPID(-600,-1200,-570,-1200);
    		Timer.delay(0.5);
    		pid_init();
    		AutoPID(-300,-800,-300,-800);
    		Timer.delay(0.5);
    		pid_init();
    		AutoPID(-300,-300,300,300);
    		Enc_l.reset();
    		Enc_r.reset();
    		pid_init();
    		Timer.delay(1);
    		AutoPID(600,1000,600,1000);
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
