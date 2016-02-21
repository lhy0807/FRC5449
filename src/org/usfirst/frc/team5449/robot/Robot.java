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
        	pid_init();
        	Timer.delay(1);
    		AutoPID(300,1500,300,1500);
    		Timer.delay(0.05);
    		pid_init();
    		AutoPID(-300,-1500,-300,-1500);
    		Timer.delay(0.05);
    		pid_init();
    		Timer.delay(0.05);
    		AutoPID(-300,-1000,300,1000);
    		break;
    	}
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
