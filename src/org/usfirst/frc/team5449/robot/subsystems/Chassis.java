package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.Vector2d;

/***
 * based on 4 wheeled mecanum chassis with INVERTED one-side
 */
public class Chassis extends Subsystem {
	
	private TalonSRX LeftRear;
	private TalonSRX LeftMid;
	private TalonSRX LeftFront;
	private TalonSRX RightRear;
	private TalonSRX RightMid;
	private TalonSRX RightFront;
	
    public Chassis(){
    	//define motors
    	LeftFront = new TalonSRX(RobotMap.LEFT_FRONT_MOTOR_PORT);
    	LeftMid = new TalonSRX(RobotMap.LEFT_MID_MOTOR_PORT);
    	LeftRear = new TalonSRX(RobotMap.LEFT_REAR_MOTOR_PORT);
    	RightFront = new TalonSRX(RobotMap.RIGHT_FRONT_MOTOR_PORT);
    	RightMid = new TalonSRX(RobotMap.RIGHT_MID_MOTOR_PORT);
    	RightRear = new TalonSRX(RobotMap.RIGHT_REAR_MOTOR_PORT);
    	//set inverted motors (default: RIGHT ???)
    	RightFront.setInverted(true);
    	RightMid.setInverted(true);
    	RightRear.setInverted(true);
    	//motor Mode    	
    	//build mecanum drive
    	//mecanumDrive = new MecanumDrive(LeftFront, LeftRear, RightFront, RightRear);
    }	
	/*
	public void BaseMecDriveTest(){
		//NOTE: deadzone should be directly applied to x y and z in here, 
			//currently there is no deadzone for testing 
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		double z = Robot.oi.getJoystickZ();
		double[] ChasisPower = new double[4];
		//get raw
		ChasisPower[RobotMap.leftfront-1] = x+y+z;
		ChasisPower[RobotMap.rightfront-1] = -x+y-z;
		ChasisPower[RobotMap.leftrear-1] = -x+y+z;
		ChasisPower[RobotMap.rightrear-1] = x+y-z;
		//trim 
		double trim_max = Math.max(Math.max(Math.max(Math.abs(ChasisPower[0]),Math.abs(ChasisPower[1])),
                Math.max(Math.abs(ChasisPower[2]),Math.abs(ChasisPower[3]))),1);
		for(int i=0; i<ChasisPower.length;i++){
			ChasisPower[i] /= trim_max;
		}
		//set
		LeftFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftfront-1]);
		RightFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightfront-1]);
		LeftRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftrear-1]);
		RightRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightrear-1]);
	}
	/**on the basis that the Robot is facing the court and has 0-gyro (calibrated) at start 
	 * NEVER BEEN TESTED, CAUTION! 
	 * */
    /*
	public void HeadlessMecDriveTest(){
		//NOTE: deadzone should be directly applied to x y and z in here, 
			//currently there is no deadzone for testing 
		double padX = Robot.oi.getJoystickX();
		double padY = Robot.oi.getJoystickY();
		double padZ = Robot.oi.getJoystickZ();
		double angle = Robot.oi.getGyroZ();
		double[] ChasisPower = new double[4];
		Vector2d driveVector = new Vector2d(padX, padY);
		//rotate vector to find heading
		driveVector.rotate(-angle);
		//set raw power
		ChasisPower[RobotMap.leftfront-1] = driveVector.x+driveVector.y+padZ;
		ChasisPower[RobotMap.rightfront-1] = -driveVector.x+driveVector.y-padZ;
		ChasisPower[RobotMap.leftrear-1] = -driveVector.x+driveVector.y+padZ;
		ChasisPower[RobotMap.rightrear-1] = driveVector.x+driveVector.y-padZ;
		//trim raw power to 1 (max speed)
		double trim_max = Math.max(Math.max(Math.max(Math.abs(ChasisPower[0]),Math.abs(ChasisPower[1])),
                Math.max(Math.abs(ChasisPower[2]),Math.abs(ChasisPower[3]))),1);
		for(int i=0; i<ChasisPower.length;i++){
			ChasisPower[i] /= trim_max;
		}
		//run with voltage
			//see if this work later (with Encoder): ControlMode.Velocity
		LeftFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftfront-1]);
		RightFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightfront-1]);
		LeftRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftrear-1]);
		RightRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightrear-1]);
	}
	
	public void AutonomousTimedMecDriveTest(double x, double y, double z, double seconds){
		//TODO		
	}
	*/
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	public void stop(){
		LeftFront.set(ControlMode.PercentOutput,0);
		LeftMid.set(ControlMode.PercentOutput, 0);
		LeftRear.set(ControlMode.PercentOutput,0);
		RightFront.set(ControlMode.PercentOutput,0);
		RightMid.set(ControlMode.PercentOutput, 0);
		RightRear.set(ControlMode.PercentOutput,0);
	}
	 
}

