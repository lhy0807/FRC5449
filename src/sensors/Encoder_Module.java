package sensors;

import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;

public class Encoder_Module {
	
	private Encoder E0,E1,E2;
	//double[][] X_Array = {{},{},{}}; 
	//private Matrix X = new Matrix(X_Array);
	
	public Encoder_Module(){
		this.E0 = new Encoder(RobotMap.EM_ENCODER_0_PORT_A,RobotMap.EM_ENCODER_0_PORT_B);
		this.E1 = new Encoder(RobotMap.EM_ENCODER_1_PORT_A,RobotMap.EM_ENCODER_1_PORT_B);
		this.E2 = new Encoder(RobotMap.EM_ENCODER_2_PORT_A,RobotMap.EM_ENCODER_2_PORT_B);
	}
	
	
	

}
