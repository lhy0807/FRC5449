package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.CameraServer;

public class RobotAssist extends SampleRobot{
    static RobotDrive myRobot;  // class that handles basic drive operations
    static Joystick  Tank;  // set to ID 1 in DriverStation
    static Joystick Arm; // set to ID 2 in DriverStation
    static CANTalon mot_l1; //define left motor 1
    static CANTalon mot_l2; //define left motor 2
    static CANTalon mot_r1; //define right motor 1
    static CANTalon mot_r2; //define right motor 2
    static double TankPower=0.2; //define default TankPower
    static double Tankspeed1=0.2; //define low TankPower value
    static double Tankspeed2=0.5; //define high TankPower value
    static int PowerChangerBot = 1; //define the PowerChanger bottom
    static boolean button_status = false; //Process value for PowerChanger
    static boolean reverse_button_status = false; //Process value for reverse_mode
    static Compressor compressor1; //define compressor
    static Solenoid ds1; //define solenoid 1
    static Solenoid ds2; //define solenoid 2
    static CameraServer server; //define camera server
    static boolean reverse_mode = false; //define default reverse_mode status
    static int reverse_mode_Bot = 2; //define reverse_mode bottom
    
    public void init(){
    myRobot = new RobotDrive(0, 1);
    myRobot.setExpiration(0.1);
    Tank = new Joystick(0);
    Arm = new Joystick(1);
    mot_l1 = new CANTalon(3);
    mot_l2 = new CANTalon(4);
    mot_r1 = new CANTalon(1);
    mot_r2 = new CANTalon(2); 
    compressor1 = new Compressor(0);
    ds1 = new Solenoid(0); 
    ds2 = new Solenoid(1);
    server = CameraServer.getInstance();     
    server.setQuality(50);
    server.startAutomaticCapture("cam0");
        
    }

    
    
    public void Accelerate(){
    	//Make the accelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.3)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.3)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.3);
    	mot_r2.set(Tank.getRawAxis(5)*0.3);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.35)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.35)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.35);
    	mot_r2.set(Tank.getRawAxis(5)*0.35);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.4)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.4)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.4);
    	mot_r2.set(Tank.getRawAxis(5)*0.4);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.45)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.45)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.45);
    	mot_r2.set(Tank.getRawAxis(5)*0.45);
    	Timer.delay(0.25);
    }
    
    public void Decelerate(){
    	//Make the decelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.45)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.45)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.45);
    	mot_r2.set(Tank.getRawAxis(5)*0.45);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.4)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.4)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.4);
    	mot_r2.set(Tank.getRawAxis(5)*0.4);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.35)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.35)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.35);
    	mot_r2.set(Tank.getRawAxis(5)*0.35);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.3)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.3)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*0.3);
    	mot_r2.set(Tank.getRawAxis(5)*0.3);
    	Timer.delay(0.25);
    }
    
    public void NormalDirection(){
    	mot_l1.set(Tank.getRawAxis(1)*(-TankPower)*(1.25));
    	mot_l2.set(Tank.getRawAxis(1)*(-TankPower)*(1.25));
    	mot_r1.set(Tank.getRawAxis(5)*TankPower);
    	mot_r2.set(Tank.getRawAxis(5)*TankPower);
    }
    
    public void ReverseDirection(){
    	mot_l1.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_l2.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_r1.set(Tank.getRawAxis(1)*-(TankPower)*(1.5));
    	mot_r2.set(Tank.getRawAxis(1)*-(TankPower)*(1.5));
    }

	

}
