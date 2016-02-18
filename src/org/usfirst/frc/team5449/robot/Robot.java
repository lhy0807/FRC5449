package org.usfirst.frc.team5449.robot;

public class Robot extends TankAssist {

    public void operatorControl() {    	
    	init();
        while (isOperatorControl() && isEnabled()) { 
        	intake();//intake module
        	shooter();//shooter module
        	go();//Tank module
        }
    }

}
