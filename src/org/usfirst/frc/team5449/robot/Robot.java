package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.CompressorOff;
import org.usfirst.frc.team5449.robot.command.CompressorOn;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.DriveTo;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeIn2;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.Intake_Release;
import org.usfirst.frc.team5449.robot.command.LifterStop;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.LifterToUp;
import org.usfirst.frc.team5449.robot.command.NavigateTo;
import org.usfirst.frc.team5449.robot.command.RecvGamedata;
import org.usfirst.frc.team5449.robot.command.Release_Cube;
import org.usfirst.frc.team5449.robot.command.TurnTo;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_L_Blockonly;
import org.usfirst.frc.team5449.robot.commandGroup.AutonomousGroup;
import org.usfirst.frc.team5449.robot.commandGroup.Initialize_block;
import org.usfirst.frc.team5449.robot.subsystems.Camera;
import org.usfirst.frc.team5449.robot.subsystems.Chassis;
import org.usfirst.frc.team5449.robot.subsystems.Climber;
import org.usfirst.frc.team5449.robot.subsystems.Holder;
import org.usfirst.frc.team5449.robot.subsystems.Intake;
import org.usfirst.frc.team5449.robot.subsystems.Lifter;

import Calibration.CB_core;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import pathfinding.Simulator;
import sensors.AnalogUltraSonic;
import sensors.EncoderModule;
import sensors.Encoder_Module;
import sensors.Gyro;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	//RobotDrive myRobot = new RobotDrive(0, 1);
	//public static Robot r = new Robot();
	public static OI oi;
	public static Chassis chassis;
	public static Climber climber = new Climber();
	public static Lifter lifter = new Lifter();
	public static Intake intake = new Intake();
	public static Holder holder = new Holder();
	
	public static Encoder_Module e1 = new Encoder_Module();
	
	public static CameraServer server = CameraServer.getInstance();
	public static UsbCamera c1 = new UsbCamera("USB Camera 0",0);
	private static AnalogUltraSonic u1 = new AnalogUltraSonic(0);
	private static CB_core cb = new CB_core();
	private static Timer timer = new Timer();
	private static double last_calibration_time = 0;
	private static SendableChooser<double[]> Field_pos_chooser = new SendableChooser();
	public static boolean[] Game_data = {false,false,false};//false = left
	//Autonomous 
	Command AutonomousCommand;
	//Parameters
	@Override
	public void robotInit() {
		Field_pos_chooser.addDefault("LEFT", new double[]{2300,340});
		Field_pos_chooser.addObject("MIDDLE", new double[]{4000,340});
		c1.setResolution(960, 540);
		c1.setFPS(24);
		server.startAutomaticCapture(c1);
		oi = new OI();
		chassis = new Chassis();
		//command
		AutonomousCommand = new AutonomousGroup();
		timer.reset();
		timer.start();
		Scheduler.getInstance().removeAll();
	}
	@Override
	public void autonomousInit() {
		Scheduler.getInstance().removeAll();
	}
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopInit() {

		e1.reset();
		Gyro.reset();
		double offset;
		while (Gyro.getAngle() == 1440){
		}
		
		Gyro.set_offset(-Gyro.getAngle());
		e1.reset();
		e1.setfieldOffset(Field_pos_chooser.getSelected());
		
		this.chassis.TargetHeading = 0;
		new RecvGamedata().start();
		Scheduler.getInstance().removeAll();
		AutonomousCommand.cancel();
		lifter.ResetEncoders();
		this.chassis.reset();
		this.cb = new CB_core();
		cb.loadFRCfield();
		double[] us_pos = {0.16,-0.246};
		cb.add_sensor(us_pos, Math.PI * 0.5, u1);
		last_calibration_time = this.timer.get();
	}
	@Override
	public void teleopPeriodic() {


		SmartDashboard.putData(new Auto_L_Blockonly());
		

		


        
        SmartDashboard.putData(new CompressorOn());
		SmartDashboard.putData(new CompressorOff());
		SmartDashboard.putData(new Initialize_block());
		SmartDashboard.putData(new TurnTo(90));
		
		//Game data
		SmartDashboard.putBoolean("SW1-1",this.Game_data[0]);
		SmartDashboard.putBoolean("SC2-1",this.Game_data[1]);
		SmartDashboard.putBoolean("SW3-1",this.Game_data[2]);
		SmartDashboard.putBoolean("SW1-2",!this.Game_data[0]);
		SmartDashboard.putBoolean("SC2-2",!this.Game_data[1]);
		SmartDashboard.putBoolean("SW3-2",!this.Game_data[2]);
		
		SmartDashboard.putNumber("E0",e1.read()[0]);
		SmartDashboard.putNumber("E1",e1.read()[1]);
		SmartDashboard.putNumber("E2",e1.read()[2]);
		
		double[] Pos = {0,0};
		Pos = e1.update();
		SmartDashboard.putNumber("X",Pos[0] * 0.1);
		SmartDashboard.putNumber("Y",Pos[1] * 0.1);
		SmartDashboard.putNumber("Heading", Gyro.getAngle());

		
		SmartDashboard.putData(new DriveDistance(4));

		SmartDashboard.putNumber("enc_larm",Robot.lifter.get_position2()[0]);
		SmartDashboard.putNumber("enc_rarm",Robot.lifter.get_position2()[1]);
		SmartDashboard.putNumber("enc_l",Robot.chassis.get()[0]);
		SmartDashboard.putNumber("enc_r",Robot.chassis.get()[1]);
		SmartDashboard.putData(new DriveDistance(4));
		double[] goal = {4,5.74};
		SmartDashboard.putData("NAVIGATE TO", new NavigateTo(goal,true));
		
		SmartDashboard.putNumber("Ultrasonic_LEFT:",u1.get());
		if (this.holder.is_holding_block()){
			SmartDashboard.putNumber("BLOCK?",100);
		}else{
			SmartDashboard.putNumber("BLOCK?",0);
		}
		
		Scheduler.getInstance().run();
	}
	@Override
	public void disabledInit(){
		Scheduler.getInstance().removeAll();
	}
	
	@Override
	public void disabledPeriodic(){
		SmartDashboard.putData(Field_pos_chooser);
	}
	/*
	 * 
	private void log(){
		//TODO TBD
	}*/
}
