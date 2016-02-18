package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotAssist extends SampleRobot{
    static RobotDrive myRobot;  // class that handles basic drive operations
    static Joystick  Tank;  // set to ID 1 in DriverStation
    static CANTalon mot_l1; //define left motor 1
    static CANTalon mot_l2; //define left motor 2
    static CANTalon mot_r1; //define right motor 1
    static CANTalon mot_r2; //define right motor 2
    static CANTalon shooter_r;
    static CANTalon shooter_l;
    static CANTalon arm_r;
    static CANTalon arm_l;
    static CANTalon intake_r;
    static CANTalon intake_l;
    static double TankPower=0.4; //define default TankPower
    static double Tankspeed1=0.4; //define low TankPower value
    static double Tankspeed2=0.7; //define high TankPower value
    static int PowerChangerBot = 1; //define the PowerChanger bottom
    static boolean button_status = false; //Process value for PowerChanger
    static boolean reverse_button_status = false; //Process value for reverse_mode
    static boolean reverse_mode = false; //define default reverse_mode status
    static int reverse_mode_Bot = 2; //define reverse_mode bottom
    static double proportion = 1.25; //define proportion of speed
    
    static Encoder Enc_l;
    static Encoder Enc_r;
    
    static double Autopower=0.5;
    static int intake_bot = 5;
    static int intake_axis = 2;
    static int shooter_start = 3;
    static int shooter_end = 4;
	
    public void init(){
    myRobot = new RobotDrive(0, 1);
    myRobot.setExpiration(0.1);
    Tank = new Joystick(0);
    mot_l1 = new CANTalon(7);
    mot_l2 = new CANTalon(9);
    mot_r1 = new CANTalon(8);
    mot_r2 = new CANTalon(1);
    shooter_r = new CANTalon(3);
    shooter_l = new CANTalon(6);
    arm_r = new CANTalon(4);
    arm_l = new CANTalon(0);
    intake_r = new CANTalon(2);
    intake_l = new CANTalon(5);
    
    Enc_r = new Encoder(0,1);
    Enc_l = new Encoder(2,3);
    }
    public void AutoGO(double position){
    	while(Enc_l.getDistance()<position){
    		mot_l1.set(-Autopower*(proportion));
        	mot_l2.set(-Autopower*(proportion));
        	mot_r1.set(Autopower);
        	mot_r2.set(Autopower);
        	Timer.delay(0.25);
    	}
    	mot_l1.set(0);
    	mot_l2.set(0);
    	mot_r1.set(0);
    	mot_r2.set(0);
    	Timer.delay(0.25);
    }


    
    
    public void Accelerate(){
    	//Make the accelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.5)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.5)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.5);
    	mot_r2.set(Tank.getRawAxis(5)*0.5);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.55)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.55)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.55);
    	mot_r2.set(Tank.getRawAxis(5)*0.55);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.6)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.6)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.6);
    	mot_r2.set(Tank.getRawAxis(5)*0.6);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.65)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.65)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.65);
    	mot_r2.set(Tank.getRawAxis(5)*0.65);
    	Timer.delay(0.25);
    }
    
    public void Decelerate(){
    	//Make the decelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.65)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.65)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.65);
    	mot_r2.set(Tank.getRawAxis(5)*0.65);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.6)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.6)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.6);
    	mot_r2.set(Tank.getRawAxis(5)*0.6);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.55)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.55)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.55);
    	mot_r2.set(Tank.getRawAxis(5)*0.55);
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.5)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.5)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*0.5);
    	mot_r2.set(Tank.getRawAxis(5)*0.5);
    	Timer.delay(0.25);
    }
    
    public void NormalDirection(){
    	mot_l1.set(Tank.getRawAxis(1)*(-TankPower)*(proportion));
    	mot_l2.set(Tank.getRawAxis(1)*(-TankPower)*(proportion));
    	mot_r1.set(Tank.getRawAxis(5)*TankPower);
    	mot_r2.set(Tank.getRawAxis(5)*TankPower);
    }
    
    public void ReverseDirection(){
    	mot_l1.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_l2.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_r1.set(Tank.getRawAxis(1)*-(TankPower)*(1.5));
    	mot_r2.set(Tank.getRawAxis(1)*-(TankPower)*(1.5));
    }
    
    public void EncoderTest(){
		SmartDashboard.getNumber("Right Encoder Rate", Enc_r.getRate());
		SmartDashboard.getNumber("Right Encoder Distance", Enc_r.getDistance());
		SmartDashboard.getNumber("Right Encoder Period", Enc_r.getPeriod());
		SmartDashboard.getNumber("Right Encoder pidGet", Enc_r.pidGet());
		SmartDashboard.getNumber("Right Encoder Get", Enc_r.get());
		SmartDashboard.getNumber("Right Encoder Get", Enc_r.getRaw());
		SmartDashboard.getNumber("Right Encoder EncodingScale", Enc_r.getEncodingScale());
		SmartDashboard.getNumber("Right Encoder FPGAIndex", Enc_r.getFPGAIndex());
		SmartDashboard.getNumber("Right Encoder SamplesToAverage", Enc_r.getSamplesToAverage());

		



		
    }

}
