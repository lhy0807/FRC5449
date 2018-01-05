package org.usfirst.frc.team5449.robot.subsystems;

import org.usfirst.frc.team5449.robot.OI;
import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.command.Drive;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Chasis extends Subsystem {
	
	private TalonSRX LeftRear;
	private TalonSRX LeftFront;
	private TalonSRX RightRear;
	private TalonSRX RightFront;
	
    public Chasis(){
    	//define motors
    	LeftFront = new TalonSRX(OI.leftfront);
    	LeftRear = new TalonSRX(OI.leftrear);
    	RightFront = new TalonSRX(OI.rightfront);
    	RightRear = new TalonSRX(OI.rightrear);
    	//set inverted motors (default: RIGHT ???)
    	RightFront.setInverted(true);
    	RightRear.setInverted(true);
    	//motor Mode    	
    	//build mecanum drive
    	//mecanumDrive = new MecanumDrive(LeftFront, LeftRear, RightFront, RightRear);
    }	
	
	public void testDrive(){
		double x = Robot.oi.getJoystickX();
		double y = Robot.oi.getJoystickY();
		double z = Robot.oi.getJoystickZ();
		double[] ChasisPower = new double[4];
		ChasisPower[OI.leftfront-1] = x+y+z;
		ChasisPower[OI.rightfront-1] = -x+y-z;
		ChasisPower[OI.leftrear-1] = -x+y+z;
		ChasisPower[OI.rightrear-1] = x+y-z;
		double trim_max = Math.max(Math.max(Math.max(Math.abs(ChasisPower[0]),Math.abs(ChasisPower[1])),
                Math.max(Math.abs(ChasisPower[2]),Math.abs(ChasisPower[3]))),1);
		for(int i=0; i<ChasisPower.length;i++){
			ChasisPower[i] /= trim_max;
		}
		LeftFront.set(ControlMode.PercentOutput,ChasisPower[OI.leftfront-1]);
		RightFront.set(ControlMode.PercentOutput,ChasisPower[OI.rightfront-1]);
		LeftRear.set(ControlMode.PercentOutput,ChasisPower[OI.leftrear-1]);
		RightRear.set(ControlMode.PercentOutput,ChasisPower[OI.rightrear-1]);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}
	
	/*public void defaultMecanum(){
		mecanumDrive.driveCartesian(Robot.oi.getJoystickY(), 
				Robot.oi.getJoystickX(), Robot.oi.getJoystickZ());
	}
	public void fieldMecanum(){
		//NOT configured!!!!
		double angle = Robot.oi.getGyroZ();
		mecanumDrive.driveCartesian(Robot.oi.getJoystickY(), 
				Robot.oi.getJoystickX(), Robot.oi.getJoystickZ(), angle);
	}
	public void polarMecanum(double magnitude, double angle, double zRotation){
		//NOT configured!!!!
		mecanumDrive.drivePolar(magnitude, angle, zRotation);
	}*/
	
	public void stop(){
		LeftFront.set(ControlMode.PercentOutput,0);
		RightFront.set(ControlMode.PercentOutput,0);
		LeftRear.set(ControlMode.PercentOutput,0);
		RightRear.set(ControlMode.PercentOutput,0);
	}
	 
}
