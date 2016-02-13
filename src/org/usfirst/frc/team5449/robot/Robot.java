package org.usfirst.frc.team5449.robot;

public class Robot extends TankAssist {

    public void operatorControl() {
    	
    	init();
        //myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) { 
        	//control the solenoids via compressor, need to modify
        	if (Tank.getRawButton(3)) {
        		compressor1.setClosedLoopControl(true);
        		ds1.set(false);
        		ds2.set(true);
        	}
        	else if (Tank.getRawButton(4)) {
        		compressor1.setClosedLoopControl(true);
        		ds1.set(true);
        		ds2.set(false);
        	}
        	else {
        		compressor1.setClosedLoopControl(false);
        	}
        	
        	go();// wait for a motor update time
        	
        
        }
    }

}
