package sensors;

import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Encoder_Module {
	
	private Encoder E0,E1,E2;
	double[] lestenc = {0,0,0};
	private double[] offset_vector = {0,120.5};
	double angle_t = 0;
	double[] Position = {0,0};
	double X_temp = 0,Y_temp = 0;
	private double[] offsets = {0,0};
	private double[] field_offsets = {2300,340};
	//double[][] X_Array = {{},{},{}}; 
	//private Matrix X = new Matrix(X_Array);
	
	public Encoder_Module(){
		this.E0 = new Encoder(RobotMap.EM_ENCODER_0_PORT_A,RobotMap.EM_ENCODER_0_PORT_B);
		this.E1 = new Encoder(RobotMap.EM_ENCODER_1_PORT_A,RobotMap.EM_ENCODER_1_PORT_B);
		this.E2 = new Encoder(RobotMap.EM_ENCODER_2_PORT_A,RobotMap.EM_ENCODER_2_PORT_B);
	}
	
	public double[] read(){
		double[] val = {0,0,0};
		val[0] = this.E0.get();
		val[1] = this.E1.get();
		val[2] = this.E2.get();
		return val;
	}
	
	public void reset(){
		this.E0.reset();
		this.E1.reset();
		this.E2.reset();
		lestenc[0] = 0;
		lestenc[1] = 0;
		lestenc[2] = 0;
		angle_t = 0;
		Position[0] = 0;
		Position[1] = 0;
		X_temp = 0;
		Y_temp = 0;
		this.offsets[0] = 0;
		this.offsets[1] = 0;
		field_offsets[0] = 2300;
		field_offsets[1] = 400;	
	}
	
	public void setOffset(double[] Offsets){
		this.offsets[0] += Offsets[0];
		this.offsets[1] += Offsets[1];
	}
	
	public void setfieldOffset(double[] Offsets){
		this.field_offsets = Offsets;
	}
	
	public double[] get(double Theta){
		double[] val = {0,0};
		val[0] = Position[0];
		val[1] = Position[1];
		
		double[] rotation_offset = {0,0};
		
		rotation_offset[0] = Rotate(this.offset_vector,Theta)[0];
		rotation_offset[1] = Rotate(this.offset_vector,Theta)[1];
		
		val[0] += this.offsets[0] + this.field_offsets[0] + rotation_offset[0];
		val[1] += this.offsets[1] + this.field_offsets[1] + rotation_offset[1];
		return val;
	}
	
	
	public double[] update(){

		double theta = Math.toRadians(Gyro.getAngle());
		double[] encval = read();
		double[] temp = {encval[0] - lestenc[0],encval[1] - lestenc[1],encval[2] - lestenc[2]};
		

		double relative_angle2 =  0;
		relative_angle2 = ((theta + angle_t) * 0.5);
		  
		if (relative_angle2 < -Math.PI){
		    relative_angle2 += 2*Math.PI;
		}
		if (relative_angle2 > Math.PI){
			relative_angle2 -= 2*Math.PI;
		}  
 
		double cosine = Math.cos(theta),sine = Math.sin(theta);
		Position[0] += X_temp * cosine - Y_temp * sine;
		Position[1] += X_temp * sine + Y_temp * cosine;

		X_temp = (0.50000 * temp[0] - 1.00000 * temp[1] + 0.50000 * temp[2]);
		Y_temp = (-0.50000 * temp[0] + 0 * temp[1] + 0.50000 * temp[2]);
		X_temp = X_temp * Math.PI/500.0f*29.00f;
		Y_temp = Y_temp * Math.PI/500.0f*29.00f;
		angle_t = theta;
		lestenc[0] = encval[0];
		lestenc[1] = encval[1];
		lestenc[2] = encval[2];
		
		double[] val = {0,0};
		val[0] = Position[0];
		val[1] = Position[1];
		
		double[] rotation_offset = {0,0};
		
		rotation_offset[0] = Rotate(this.offset_vector,theta)[0];
		rotation_offset[1] = Rotate(this.offset_vector,theta)[1];
		
		val[0] += this.offsets[0] + this.field_offsets[0] + rotation_offset[0];
		val[1] += this.offsets[1] + this.field_offsets[1] + rotation_offset[1];
		
		return val;
	}
	public double[] Rotate(double[] Vector,double Radius){
		//Positive angle means rotating counterclockwise
		//angle in radius
		//tested
		double[] ans = {0,0};
		ans[0] = Math.cos(Radius) * Vector[0] - Math.sin(Radius) * Vector[1];
		ans[1] = Math.sin(Radius) * Vector[0] + Math.cos(Radius) * Vector[1];
		return ans;
	}
	
	
	

}
