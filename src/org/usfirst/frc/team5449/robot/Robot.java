package org.usfirst.frc.team5449.robot;

import org.usfirst.frc.team5449.robot.command.Center_test;
import org.usfirst.frc.team5449.robot.command.CompressorOff;
import org.usfirst.frc.team5449.robot.command.CompressorOn;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.DriveTo;
import org.usfirst.frc.team5449.robot.command.Friction_test;
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
import org.usfirst.frc.team5449.robot.command.chassis_stop;
import org.usfirst.frc.team5449.robot.command.lifter_stop;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_L_Blockonly;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_L_Blockonly2;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_R_Switch_fast;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_R_Blockonly;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_R_Scale_fast;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_xL_Scale;
import org.usfirst.frc.team5449.robot.commandGroup.Auto_xR_Scale;
import org.usfirst.frc.team5449.robot.commandGroup.AutonomousGroup;
import org.usfirst.frc.team5449.robot.commandGroup.Drive_To_Left_Scale;
import org.usfirst.frc.team5449.robot.commandGroup.Initialize_block;
import org.usfirst.frc.team5449.robot.commandGroup.NavigateTo_A;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_Pos1_L_SC;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_Pos1_L_SW;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_Pos1_R_SC;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_Pos1_R_SW;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_PosMid_L_SW;
import org.usfirst.frc.team5449.robot.commandGroup.New_Auto_PosMid_R_SW;
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
import edu.wpi.first.wpilibj.DriverStation;
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
	
	public static Chassis chassis = new Chassis();
	public static Climber climber = new Climber();
	public static Lifter lifter = new Lifter();
	public static Intake intake = new Intake();
	public static Holder holder = new Holder();
	public static OI oi = new OI();
	public static Encoder_Module e1;
	
	public static CameraServer server = CameraServer.getInstance();
	public static UsbCamera c1 = new UsbCamera("USB Camera 0",0);
	
	private static AnalogUltraSonic u_left = new AnalogUltraSonic(0);
	//private static AnalogUltraSonic u_back = new AnalogUltraSonic(1);
	private static AnalogUltraSonic u_right = new AnalogUltraSonic(2);
	
	private static CB_core cb = new CB_core();
	private static Timer timer = new Timer();
	private static double last_calibration_time = 0;
	//private static SendableChooser<double[]> Field_pos_chooser = new SendableChooser();
	private static SendableChooser<int []> Autonomous_target = new SendableChooser();
	
	public static boolean[] Game_data = {false,false,false};//false = left
	
	public static double[] offset_temp = {1254,400};
	//Autonomous 
	Command AutonomousCommand;
	//Parameters
	@Override
	public void robotPeriodic(){
		SmartDashboard.putNumber("testing data", 123456);
	}
	
	
	@Override
	public void robotInit() {
		//Field_pos_chooser.addDefault("LEFT", new double[]{2300,340});
		//Field_pos_chooser.addObject("MIDDLE", new double[]{4000,340});
		Autonomous_target.addObject("Switch", new int[]{0});
		Autonomous_target.addDefault("Scale", new int[]{1});
		Autonomous_target.addObject("Switch_Fast", new int[]{2});
		Autonomous_target.addObject("Scale_Fast", new int[]{3});
		Autonomous_target.addObject("Switch_Mid", new int[]{4});
		lifter.ResetEncoders();
		e1 = new Encoder_Module();
		//c1.setResolution(960, 540);
		//c1.setFPS(24);
		server.startAutomaticCapture(c1);
		
		//command

		
		Scheduler.getInstance().removeAll();
	}
	@Override
	public void autonomousInit() {
		
		Scheduler.getInstance().removeAll();//cancel all commands
				
		e1.reset();//encoder module reset
		e1.setfieldOffset(new double[]{1254,400});//set start position
		
		String gamedata;//get game data, false for left
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		Robot.Game_data[0] = gamedata.charAt(0) == 'R';
		Robot.Game_data[1] = gamedata.charAt(1) == 'R';
		Robot.Game_data[2] = gamedata.charAt(2) == 'R';
		
		
		int[] auto_mode = Autonomous_target.getSelected();
		switch (auto_mode[0]){
		case 0://slow auto for switch
			SmartDashboard.putString("CURRENT_MODE", "Switch");
			if (Game_data[0]){
				AutonomousCommand = new New_Auto_Pos1_R_SW();
			}else{
				AutonomousCommand = new New_Auto_Pos1_L_SW();
			}
			break;
		case 1://slow auto for scale
			SmartDashboard.putString("CURRENT_MODE", "Scale");
			if (Game_data[1]){
				AutonomousCommand = new New_Auto_Pos1_R_SC();
			}else{
				AutonomousCommand = new New_Auto_Pos1_L_SC();
			}
			break;
		case 2://fast auto for switch
			SmartDashboard.putString("CURRENT_MODE", "Switch_Fast");
			if (Game_data[0]){
				AutonomousCommand = new New_Auto_Pos1_R_SW();
			}else{
				AutonomousCommand = new New_Auto_Pos1_L_SW();
			}
			break;
		case 3://fast auto for scale
			SmartDashboard.putString("CURRENT_MODE", "Scale_Fast");
			if (Game_data[1]){
				AutonomousCommand = new New_Auto_Pos1_R_SC();
			}else{
				AutonomousCommand = new New_Auto_Pos1_L_SC();
			}
			break;
		case 4:
			if (Game_data[0]){
				AutonomousCommand = new New_Auto_PosMid_R_SW();
			}else{
				AutonomousCommand = new New_Auto_PosMid_L_SW();
			}
			break;
		}
		
		if (AutonomousCommand != null){
			AutonomousCommand.start();//start auto command.
		}
	}
	@Override
	public void autonomousPeriodic() {
		double[] Pos = {0,0};
		Pos = e1.update();//update encoder module.
		SmartDashboard.putNumber("X",Pos[0] * 0.1);
		SmartDashboard.putNumber("Y",Pos[1] * 0.1);
		Scheduler.getInstance().run();
	}
	@Override
	public void teleopInit() {
		Scheduler.getInstance().removeAll();//remove auto commands.
		SmartDashboard.putData(new Initialize_block());
		String gamedata;
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		Robot.Game_data[0] = gamedata.charAt(0) == 'R';
		Robot.Game_data[1] = gamedata.charAt(1) == 'R';
		Robot.Game_data[2] = gamedata.charAt(2) == 'R';
		
		this.chassis.TargetHeading = 0;
		//lifter.ResetEncoders();
		this.chassis.reset();
		this.cb = new CB_core();//initialize calibration
		cb.loadFRCfield();
		double[] Coordinates1 = {0.16, 0.246};
		double[] Coordinates2 = {0,0};
		double[] Coordinates3 = {0.16,-0.246};
		
		cb.add_sensor(Coordinates1, -Math.PI *0.5, this.u_left);
		//cb.add_sensor(Coordinates2, Math.PI, this.u_back);
		cb.add_sensor(Coordinates3, Math.PI * 0.5, this.u_right);
		timer.reset();
		timer.start();
	}
	@Override
	public void teleopPeriodic() {
		
		
		
		
		SmartDashboard.putNumber("u_left", u_left.get());
		SmartDashboard.putNumber("u_right", u_right.get());
		
        SmartDashboard.putData(new CompressorOn());
		SmartDashboard.putData(new CompressorOff());
		SmartDashboard.putData(new chassis_stop());
		SmartDashboard.putData(new lifter_stop());
		
		//Game data
		SmartDashboard.putBoolean("SW1-1",this.Game_data[0]);
		SmartDashboard.putBoolean("SC2-1",this.Game_data[1]);
		SmartDashboard.putBoolean("SW3-1",this.Game_data[2]);
		SmartDashboard.putBoolean("SW1-2",!this.Game_data[0]);
		SmartDashboard.putBoolean("SC2-2",!this.Game_data[1]);
		SmartDashboard.putBoolean("SW3-2",!this.Game_data[2]);
		
		double[] Pos = {0,0};//update position
		double Heading = Gyro.getAngle();
		Pos = e1.update();
		SmartDashboard.putNumber("X",Pos[0] * 0.1);
		SmartDashboard.putNumber("Y",Pos[1] * 0.1);
		SmartDashboard.putNumber("Heading", -Heading);

		//calibration position
		double[] pos_cali = {0,0};
		pos_cali[0] = Pos[0] * 0.001f;
		pos_cali[1] = Pos[1] * 0.001f;
		double[] calibration = {0,0};
		calibration = cb.Update(pos_cali, -Math.toRadians(Heading), timer.get());
		double[] temp_cali = {0,0};
		temp_cali[0] = calibration[0] * 1000.0d;
		temp_cali[1] = calibration[1] * 1000.0d;
		e1.addOffset(temp_cali);
		
		//encoder datas
		SmartDashboard.putNumber("enc_larm",Robot.lifter.get_position2()[0]);
		SmartDashboard.putNumber("enc_rarm",Robot.lifter.get_position2()[1]);
		SmartDashboard.putNumber("enc_l",Robot.chassis.get()[0]);
		SmartDashboard.putNumber("enc_r",Robot.chassis.get()[1]);

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
		SmartDashboard.putData(Autonomous_target);
	}
}
