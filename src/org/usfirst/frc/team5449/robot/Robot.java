package org.usfirst.frc.team5449.robot;

public class Robot extends TankAssist {

    public void operatorControl() {
    	
    	init();
        //myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) { 
        	intake();
        	shooter();
        	go();// wait for a motor update time
        	
        
        }
    }

}
