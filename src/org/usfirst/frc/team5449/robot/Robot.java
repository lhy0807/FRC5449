
package org.usfirst.frc.team5449.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
	public class Robot extends SampleRobot {
		
	static RobotDrive myRobot;  // class that handles basic drive operations
    static Joystick  Tank;  // set to ID 1 in DriverStation
    static Joystick Shoot;
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
    static Encoder Enc_arm_l;
    static Encoder Enc_arm_r;
    
    static int intake_bot = 2;
    static int outake_bot = 3;
    static int shooter_bot = 1;
    static int arm_up = 4;
    static int arm_down = 6;
    static int arm_up_slow = 11;
    static int arm_down_slow = 12;
    static int Enc_reset = 12;
    static int shooter_reverse = 5;

    static double kp_l = 0.01;
    static double ki_l = 0.001;
    static double kd_l = 0;
    static double f_error_1_l = 0;
    static double f_error_2_l = 0;
    static double f_error_3_l = 0;
    static double f_control_output_l = 0;
    static double f_control_change_l = 0;
	
    static double kp_r = 0.01;
    static double ki_r = 0.001;
    static double kd_r = 0;
    static double f_error_1_r = 0;
    static double f_error_2_r = 0;
    static double f_error_3_r = 0;
    static double f_control_output_r = 0;
    static double f_control_change_r = 0;
	
    public Robot() {
    	myRobot = new RobotDrive(0, 1);
        myRobot.setExpiration(0.1);
        Tank = new Joystick(0);
        Shoot = new Joystick(1);
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
    
    public void robotInit() {
            }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the if-else structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    public void autonomous() {
    	while (isAutonomous() && isEnabled()){
    	Auto1();
    	//Auto2();
    	break;
    	}
    	Enc_l.reset();
		Enc_r.reset();
    
    	}
    public void Auto1(){
    	mot_l1.set(0.25);
    	mot_l2.set(0.25);
    	mot_r1.set(-0.25);
    	mot_r2.set(-0.25);
		Timer.delay(0.1);
		
		Enc_l.reset();
		Enc_r.reset();	
    	pid_init();
    	
    	arm_l.set(0.31);
		arm_r.set(0.31);
		
		pid_init();
		AutoPID(800,1000,850,1050);
		
		arm_l.set(0);
		arm_r.set(0);
		
		pid_init();
		AutoPID(600,2500,600,2500);
		
		shooter_r.set(-0.4);
		shooter_l.set(0.4);
		arm_l.set(-0.8);
		arm_r.set(-0.8);
		Timer.delay(0.75);
		arm_l.set(0);
		arm_r.set(0);
		
		shooter_r.set(-0.8);
		shooter_l.set(0.8);
		
		pid_init();
		AutoPID(700,90,-700,-90);
		Timer.delay(0.1);
		
		
		
		
		shooter_r.set(-1);
		shooter_l.set(1);
		Timer.delay(2);

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
    }
    
    
    public void Auto2(){
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
		AutoPID(-900,-1000,-800,-1050);
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
//		mot_l1.set(0.1);
//    	mot_l2.set(0.1);
//    	mot_r1.set(-0.1);
//    	mot_r2.set(-0.1);
		
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
    }
    

    /**
     * Runs the motors with arcade steering.
     */
    
    
    public void operatorControl() {
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
    
    public void go(){
		//PowerChanger
		if(Tank.getRawButton(PowerChangerBot)) {
			if (button_status==false) {
				if (TankPower==Tankspeed1) {
					TankPower=Tankspeed2;
					//Accelerate
					if (reverse_mode==false) {
					Accelerate();
					}
				}
				else {
					TankPower=Tankspeed1;
					//Decelerate
					if (reverse_mode==false) {
					Decelerate();
					}
				}
			}
			button_status=true;
			}
	else {
		button_status=false;
	}
		//reverse_mode changer
	if (Tank.getRawButton(reverse_mode_Bot)) {
		if (reverse_button_status==false) {
			if (reverse_mode==false) {
				reverse_mode=true;
			}
			else {
				reverse_mode=false;
			}
			reverse_button_status=true;
		}
	}
		else {
			reverse_button_status=false;
		}
	//select direction, either normal or reverse
	if (reverse_mode==false) {
		NormalDirection();		
	}
	else if (reverse_mode==true) {
		ReverseDirection();		
	}
    }
	
	public void shooter() {
		if (Shoot.getRawButton(shooter_reverse)) {
			shooter_r.set(0.2);
			shooter_l.set(-0.2);
			Timer.delay(0.05);
		}
		else if (Shoot.getRawButton(shooter_bot)) {
			shooter_r.set(-1);
			shooter_l.set(1);
			Timer.delay(0.05);
		}
		else {
			shooter_r.set(0);
			shooter_l.set(0);
		}
	}
	
	public void intake() {
		if (Shoot.getRawButton(outake_bot)) {
			intake_r.set(-1);
			intake_l.set(-1);
			Timer.delay(0.1);
		}
		if (Shoot.getRawButton(intake_bot)) {
			intake_r.set(1);
			intake_l.set(1);
			Timer.delay(0.1);
		}
		else {
			intake_r.set(0);
			intake_l.set(0);
		}
	}
	
	public void arm() {
		if(Shoot.getRawButton(arm_up)) {
			arm_r.set(1);
			arm_l.set(1);
		}
		else if(Shoot.getRawButton(arm_down)) {
			arm_r.set(-1);
			arm_l.set(-1);
		}
		else if(Shoot.getRawButton(arm_up_slow)) {
			arm_r.set(0.5);
			arm_l.set(0.5);
		}
		else if(Shoot.getRawButton(arm_down_slow)) {
			arm_r.set(-0.5);
			arm_l.set(-0.5);
		}
		else {
			arm_r.set(0);
			arm_l.set(0);
		}
		
	}
	
	public void PID_l(double pid_rate) {
    	f_error_2_l = f_error_1_l;
    	f_error_3_l = f_error_2_l;
    	f_error_1_l = pid_rate + Enc_l.getRate();
    	f_control_change_l = f_error_1_l * kp_l/1000 + //proportional term
    			(f_error_1_l - f_error_2_l) * ki_l/1000 + //integral term
    			((f_error_1_l - f_error_2_l) - (f_error_2_l - f_error_3_l)) * kd_l/1000; //differential term
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
    			(f_error_1_r - f_error_2_r) * ki_r/1000 + //integral term
    			((f_error_1_r - f_error_2_r) - (f_error_2_r - f_error_3_r)) * kd_r/1000; //differential term
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
    
    public void AutoPID(double l_pid,double l_distance,double r_pid,double r_distance){
    	
    	double r_real_distance = r_distance - Enc_r.getDistance();
    	
    	double l_real_distance = l_distance - Enc_l.getDistance();
    	
    	double r_before = Enc_r.getDistance();
    	
    	double l_before = Enc_l.getDistance();
   	
    	boolean a = true;
    	boolean b = true;
    	boolean c = true;
    	while(a){
    		SmartDashboard.putNumber("l_pid", l_pid);
    		SmartDashboard.putNumber("r_pid", r_pid);

    		EncoderTest();
    		if(r_distance>0){
    			if(r_real_distance>(-Enc_r.getDistance())){
    				PID_r(r_pid);
    	    		SmartDashboard.putString("running_status_r", "Forward running");
    	    		SmartDashboard.putNumber("r_distance", r_distance);
    	    		SmartDashboard.putNumber("r_real_distance", r_real_distance);
    	    		SmartDashboard.putNumber("Enc_r.getDistance()", Enc_r.getDistance());

    	    		
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;
    	    		SmartDashboard.putString("running_status_r", "Forward stopping");
    			}
    		}
    		if(r_distance<0){
    			if(r_real_distance<(-Enc_r.getDistance())){
    				PID_r(r_pid);
    	    		SmartDashboard.putString("running_status_r", "Backward running");
    	    		SmartDashboard.putNumber("r_distance", r_distance);
    	    		SmartDashboard.putNumber("r_real_distance", r_real_distance);
    	    		SmartDashboard.putNumber("Enc_r.getDistance()", Enc_r.getDistance());
    			}
    			else{
    				mot_r1.set(0);
        			mot_r2.set(0);
    				b = false;
    	    		SmartDashboard.putString("running_status_r", "Backward stopping");	
    			}
    		}
    		if(r_distance==0){
    			mot_r1.set(0);
    			mot_r2.set(0);
    			b=false;
	    		SmartDashboard.putString("running_status_r", "Zero stopping");	
    		}
    		if(l_distance>0){
    			if(l_real_distance>(-Enc_l.getDistance())){
    				PID_l(l_pid);
    	    		SmartDashboard.putString("running_status_l", "Forward running");
    	    		SmartDashboard.putNumber("l_distance", l_distance);
    	    		SmartDashboard.putNumber("l_real_distance", l_real_distance);
    	    		SmartDashboard.putNumber("Enc_l.getDistance()", Enc_l.getDistance());
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;
    	    		SmartDashboard.putString("running_status_l", "Forward stopping");	
    			}
    		}
    		if(l_distance<0){
    			if(l_real_distance<(-Enc_l.getDistance())){
    				PID_l(l_pid);
    	    		SmartDashboard.putString("running_status_l", "Backward running");
    	    		SmartDashboard.putNumber("l_distance", l_distance);
    	    		SmartDashboard.putNumber("l_real_distance", l_real_distance);
    	    		SmartDashboard.putNumber("Enc_l.getDistance()", Enc_l.getDistance());
    			}
    			else{
    				mot_l1.set(0);
        			mot_l2.set(0);
    				c = false;
    	    		SmartDashboard.putString("running_status_l", "Backward stopping");		
    			}
    		}
    		if(l_distance==0){
    			mot_l1.set(0);
    			mot_l2.set(0);
    			c = false;
	    		SmartDashboard.putString("running_status_l", "Zero stopping");			
    		}
    		if(b==false && c==false){
    			a=false;
	    		SmartDashboard.putString("running_status", "stopping");		
	    		mot_l1.set(0);
    			mot_l2.set(0);
    			mot_r1.set(0);
    			mot_r2.set(0);
    			break;
    			
    		}
    		else{
    			SmartDashboard.putString("running_status", "running");	
    		}
    	}

		SmartDashboard.putNumber("Enc_r.change()", Enc_r.getDistance()-r_before);
		SmartDashboard.putNumber("Enc_l.change()", Enc_l.getDistance()-l_before);

		l_real_distance=0;
		r_real_distance=0;
		l_distance=0;
		r_distance=0;
		f_control_output_l = 0;
        f_control_change_l = 0;
        f_control_output_r = 0;
        f_control_change_r = 0;

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
    
    public void pid_init() {
        kp_l = 0.01;
        ki_l = 0.001;
        kd_l = 0;
        f_error_1_l = 0;
        f_error_2_l = 0;
        f_error_3_l = 0;
        f_control_output_l = 0;
        f_control_change_l = 0;
 	
        kp_r = 0.01;
        ki_r = 0.001;
        kd_r = 0;
        f_error_1_r = 0;
        f_error_2_r = 0;
        f_error_3_r = 0;
        f_control_output_r = 0;
        f_control_change_r = 0;
  }
    /**
     * Runs during test mode
     */
    public void test() {
    }
}
