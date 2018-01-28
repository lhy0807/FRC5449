package org.usfirst.frc.team5449.robot;

public class RobotMap {
//TalonSRXs
	//chassis motors
	public static final int LEFT_FRONT_MOTOR_PORT = 3;
	public static final int LEFT_MID_MOTOR_PORT = 2;
	public static final int LEFT_REAR_MOTOR_PORT = 1;
	public static final int RIGHT_FRONT_MOTOR_PORT = 10;
	public static final int RIGHT_MID_MOTOR_PORT = 9;
	public static final int RIGHT_REAR_MOTOR_PORT = 8;
	//Intake motors
	public static final int LEFT_INTAKE_PORT = 6;
	public static final int RIGHT_INTAKE_PORT = 13;
	public static final int MID_INTAKE_PORT = 7;
	//Lifter Motors
	public static final int LIFTER_LEFT_MOTOR_PORT = 4;
	public static final int LIFTER_RIGHT_MOTOR_PORT = 11;
	//climber motors
	public static final int CLIMBER_MOTOR_1_PORT = 5;
	public static final int CLIMBER_MOTOR_2_PORT = 12;
//Pneumatics
	//PCM module
	public static final int PCM_PORT = 16;
	//Intake solenoids 
	public static final int INTAKE_LEFT_SOLENOID_PORT = 0;
	public static final int INTAKE_RIGHT_SOLENOID_PORT = 1;
	//Holder
	public static final int HOLDER_BLOCK_PUSHER1_PORT = 2;
	public static final int HOLDER_BLOCK_PUSHER2_PORT = 3;
//Sensors
	//gyro
	public static final int GYRO_PORT = 1;
	//proximity switches
	public static final int LIFTER_PS_LOW_PORT = 4;
	public static final int LIFTER_PS_HIGH_PORT = 5;
	//Encoders
	public static final int LIFTER_ENCODER_LEFT_PORT_A = 0;
	public static final int LIFTER_ENCODER_LEFT_PORT_B = 1;
	public static final int LIFTER_ENCODER_RIGHT_PORT_A = 2;
	public static final int LIFTER_ENCODER_RIGHT_PORT_B = 3;
	
//Parameters
	//Chassis TODO configure
	public static final double CHASSIS_TURNING_DEADZONE = 0.15;
	public static final double CHASSIS_MAX_PASSING_ERROR = 0;
	public static final double CHASSIS_TURNING_P = 0;
	public static final double CHASSIS_TURNING_D = 0;
	public static final double CHASSIS_TURNING_ALLOWED_ERROR = 0;
	//lifter
	public static final long LIFTER_UP_POSE = 400;
	public static final long LIFTER_MID_POSE = 60;
	public static final long LIFTER_DOWN_POSE = 0;
	public static final double LIFTER_MAXIUM_PASSING_ERROR = 20;
	public static final double LIFTER_MAXIUM_POWER = 0.7;
	public static final double LIFTER_MINIUM_POWER = 0.17;
	public static final double LIFTER_KP = 0.015;
	public static final double LIFTER_KD = 0;
	public static final double LIFTER_BALANCE_KP = 0.03;
	//Holder
	public static final double HOLDER_DELAY = 0.500;//sec
	//Climber
	public static final double CLIMBER_POWER = 1.000;
	
	//Potention field
	public static final double PF_EXPELL_CONSTANT = 1.000;
	public static final double PF_EXPELL_RANGE = 1.000; //unit: meter
	public static final double PF_ATTRACT_CONSTANT = 2.000;
	
	
	
	
}
