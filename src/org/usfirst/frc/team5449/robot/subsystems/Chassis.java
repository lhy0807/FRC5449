package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.RobotMap;
import org.usfirst.frc.team5449.robot.command.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Chassis extends Subsystem {
	
	private TalonSRX LeftRear;
	private TalonSRX LeftFront;
	private TalonSRX RightRear;
	private TalonSRX RightFront;
	
    public Chassis(){
    	//define motors
    	LeftFront = new TalonSRX(RobotMap.leftfront);
    	LeftRear = new TalonSRX(RobotMap.leftrear);
    	RightFront = new TalonSRX(RobotMap.rightfront);
    	RightRear = new TalonSRX(RobotMap.rightrear);
    	//set inverted motors (default: RIGHT ???)
    	RightFront.setInverted(true);
    	RightRear.setInverted(true);
    	//motor Mode    	
    	//build mecanum drive
    	//mecanumDrive = new MecanumDrive(LeftFront, LeftRear, RightFront, RightRear);
    }	
	
	public void BaseMecDriveTest(){
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		double z = Robot.oi.getJoystickZ();
		double[] ChasisPower = new double[4];
		ChasisPower[RobotMap.leftfront-1] = x+y+z;
		ChasisPower[RobotMap.rightfront-1] = -x+y-z;
		ChasisPower[RobotMap.leftrear-1] = -x+y+z;
		ChasisPower[RobotMap.rightrear-1] = x+y-z;
		double trim_max = Math.max(Math.max(Math.max(Math.abs(ChasisPower[0]),Math.abs(ChasisPower[1])),
                Math.max(Math.abs(ChasisPower[2]),Math.abs(ChasisPower[3]))),1);
		for(int i=0; i<ChasisPower.length;i++){
			ChasisPower[i] /= trim_max;
		}
		LeftFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftfront-1]);
		RightFront.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightfront-1]);
		LeftRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.leftrear-1]);
		RightRear.set(ControlMode.PercentOutput,ChasisPower[RobotMap.rightrear-1]);
	}
	public void HeadlessMecDriveTest(){
		//TODO
		
	}
	public void DirectionalMecDriveTest(){
		//TODO
		
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	public void stop(){
		LeftFront.set(ControlMode.PercentOutput,0);
		RightFront.set(ControlMode.PercentOutput,0);
		LeftRear.set(ControlMode.PercentOutput,0);
		RightRear.set(ControlMode.PercentOutput,0);
	}
	 
}

