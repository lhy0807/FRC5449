package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;

public class Robot extends TankAssist {

    public void operatorControl() {    	
    	init();
        while (isOperatorControl() && isEnabled()) { 
        	intake();//intake module
        	shooter();//shooter module
        	go();//Tank module
        }
    }
    
    public void autonomous() {
    	init();
    	while (isAutonomous() && isEnabled()){
    		
    		
    	}
    }
    
    public void test() {
    	init();
    	while (isTest() && isEnabled()){
    		PID_l(50.00);
        	EncoderTest();
    	}
    }

}
