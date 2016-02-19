package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Ultrasonic;

public class RobotAssist extends SampleRobot{
    static RobotDrive myRobot;  // class that handles basic drive operations
    static Joystick  Tank;  // set to ID 1 in DriverStation
    static CANTalon mot_l1; //define left motor 1
    static CANTalon mot_l2; //define left motor 2
    static CANTalon mot_r1; //define right motor 1
    static CANTalon mot_r2; //define right motor 2
    static CANTalon shooter_r;
    static CANTalon shooter_l;
    static boolean shooter_status;
    static boolean shooter_mode;
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
    static double proportion = 0.95; //define proportion of speed
    static Ultrasonic ultra;
    
    static Encoder Enc_l;
    static Encoder Enc_r;
    
    static double Autopower=0.5;
    static int intake_bot = 5;
    static int intake_axis = 2;
    static int shooter_bot = 3;
    static int arm_up = 6;
    static int arm_down = 3;
    
    static double kp_l = 0.05;
    static double ki_l = 0;
    static double kd_l = 0;
    static double f_error_1_l = 0;
    static double f_error_2_l = 0;
    static double f_error_3_l = 0;
    static double f_control_output_l = 0;
    static double f_control_change_l = 0;
	
    static double kp_r = 0.0005;
    static double ki_r = 0;
    static double kd_r = 0;
    static double f_error_1_r = 0;
    static double f_error_2_r = 0;
    static double f_error_3_r = 0;
    static double f_control_output_r = 0;
    static double f_control_change_r = 0;
	
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
    ultra = new Ultrasonic(9,8);
    ultra.setAutomaticMode(true);
    Enc_r = new Encoder(1,0);
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
    
    public void AutoDIS(double r_power,double r_distance,double l_power,double l_distance){
    	r_distance+= Enc_r.getDistance();
    	l_distance+= Enc_l.getDistance();
    	boolean a = true;
    	boolean b = true;
    	boolean c = true;
    	while(a){
    		if(r_distance>0){
    			if(r_distance>Enc_r.getDistance()){
    				mot_r1.set(r_power);
    				mot_r2.set(r_power);
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;}
    		}
    		if(r_distance<0){
    			if(r_distance<Enc_r.getDistance()){
    				mot_r1.set(r_power);
    				mot_r2.set(r_power);
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;}
    		}
    		if(r_distance==0){
    			mot_r1.set(0);
    			mot_r2.set(0);
    			b=false;
    		}
    		if(l_distance>0){
    			if(l_distance<Enc_l.getDistance()){
    				mot_l1.set(-l_power);
    				mot_l2.set(-l_power);
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;}
    		}
    		if(l_distance<0){
    			if(l_distance>Enc_l.getDistance()){
    				mot_l1.set(-l_power);
    				mot_l2.set(-l_power);
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;}
    		}
    		if(l_distance==0){
    			mot_l1.set(0);
    			mot_l2.set(0);
    			c = false;}
    		if(b==false && c==false){
    			a=false;
    		}
    	}
    }
        
    
    public void PID_l(double pid_rate) {
    	f_error_2_l = f_error_1_l;
    	f_error_3_l = f_error_2_l;
    	f_error_1_l = pid_rate + Enc_l.getRate();
    	f_control_change_l = f_error_1_l * kp_l/1000 + //proportional term
    			(f_error_1_l - f_error_2_l) * ki_l + //integral term
    			((f_error_1_l - f_error_2_l) - (f_error_2_l - f_error_3_l)) * kd_l; //differential term
    			//add the change to control output
    	f_control_output_l += f_control_change_l;
    	
    	
    	SmartDashboard.putNumber("error1", f_error_1_l);
		SmartDashboard.putNumber("f_control_change_l", f_control_change_l);
		SmartDashboard.putNumber("f_control_output_l", f_control_output_l);

   		f_control_output_l = f_control_output_l > 1? 1:f_control_output_l;
    	f_control_output_l = f_control_output_l < -1? -1:f_control_output_l;	
    		mot_l1.set(f_control_output_l);
    		mot_l2.set(f_control_output_l);
    		Timer.delay(0.005);
    }

    public void PID_r(double pid_rate) {
    	f_error_2_r = f_error_1_r;
    	f_error_3_r = f_error_2_r;
    	f_error_1_r = -pid_rate - Enc_r.getRate();
    	f_control_change_r = f_error_1_r * kp_r/1000 + //proportional term
    			(f_error_1_r - f_error_2_r) * ki_r + //integral term
    			((f_error_1_r - f_error_2_r) - (f_error_2_r - f_error_3_r)) * kd_r; //differential term
    			//add the change to control output
    			f_control_output_r += f_control_change_r;
    		f_control_output_r = f_control_output_r > 1? 1:f_control_output_r;
    		f_control_output_r = f_control_output_r < -1? -1:f_control_output_r;	
    		
    		SmartDashboard.putNumber("error1", f_error_1_r);
    		SmartDashboard.putNumber("f_control_change_r", f_control_change_r);
    		SmartDashboard.putNumber("f_control_output_r", f_control_output_r);
    		
    		
    		mot_r1.set(f_control_output_r);
    		mot_r2.set(f_control_output_r);
    		Timer.delay(0.005);
    }
    
    public void AutoPID(double r_pid,double r_distance,double l_pid,double l_distance){
    	r_distance+= Enc_r.getDistance();
    	l_distance+= Enc_l.getDistance();
    	boolean a = true;
    	boolean b = true;
    	boolean c = true;
    	while(a){
    		if(r_distance>0){
    			if(r_distance>Enc_r.getDistance()){
    				PID_r(r_pid);
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;}
    		}
    		if(r_distance<0){
    			if(r_distance<Enc_r.getDistance()){
    				PID_r(r_pid);
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;}
    		}
    		if(r_distance==0){
    			mot_r1.set(0);
    			mot_r2.set(0);
    			b=false;
    		}
    		if(l_distance>0){
    			if(l_distance<Enc_l.getDistance()){
    				PID_l(l_pid);
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;}
    		}
    		if(l_distance<0){
    			if(l_distance>Enc_l.getDistance()){
    				PID_l(l_pid);
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;}
    		}
    		if(l_distance==0){
    			mot_l1.set(0);
    			mot_l2.set(0);
    			c = false;}
    		if(b==false && c==false){
    			a=false;
    		}
    	}
    }
    
    
    
    public void Accelerate(){
    	//Make the accelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.5));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.5));
    	mot_r1.set(Tank.getRawAxis(5)*0.5*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.5*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.55));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.55));
    	mot_r1.set(Tank.getRawAxis(5)*0.55*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.55*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.6));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.6));
    	mot_r1.set(Tank.getRawAxis(5)*0.6*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.6*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.65));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.65));
    	mot_r1.set(Tank.getRawAxis(5)*0.65*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.65*(proportion));
    	Timer.delay(0.25);
    }
    
    public void Decelerate(){
    	//Make the decelerate smoothly
    	mot_l1.set(Tank.getRawAxis(1)*(-0.65));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.65));
    	mot_r1.set(Tank.getRawAxis(5)*0.65*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.65*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.6));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.6));
    	mot_r1.set(Tank.getRawAxis(5)*0.6*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.6*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.55));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.55));
    	mot_r1.set(Tank.getRawAxis(5)*0.55*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.55*(proportion));
    	Timer.delay(0.25);
		mot_l1.set(Tank.getRawAxis(1)*(-0.5));
    	mot_l2.set(Tank.getRawAxis(1)*(-0.5));
    	mot_r1.set(Tank.getRawAxis(5)*0.5*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*0.5*(proportion));
    	Timer.delay(0.25);
    }
    
    public void NormalDirection(){
    	mot_l1.set(Tank.getRawAxis(1)*(-TankPower));
    	mot_l2.set(Tank.getRawAxis(1)*(-TankPower));
    	mot_r1.set(Tank.getRawAxis(5)*TankPower*(proportion));
    	mot_r2.set(Tank.getRawAxis(5)*TankPower*(proportion));
    }
        
    public void ReverseDirection(){
    	mot_l1.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_l2.set(Tank.getRawAxis(5)*(TankPower)*(1.5));
    	mot_r1.set(Tank.getRawAxis(1)*-(TankPower)*(1.5)*(proportion));
    	mot_r2.set(Tank.getRawAxis(1)*-(TankPower)*(1.5)*(proportion));
    }
    
    public void EncoderTest(){
		SmartDashboard.putNumber("Left Encoder Rate", Enc_l.getRate());
		SmartDashboard.putNumber("left Encoder Distance", Enc_l.getDistance());
		SmartDashboard.putNumber("Right Encoder Rate", Enc_r.getRate());
		SmartDashboard.putNumber("Right Encoder Distance", Enc_r.getDistance());
		SmartDashboard.putNumber("k", Enc_l.getRate()/Enc_r.getRate());

    }

}
