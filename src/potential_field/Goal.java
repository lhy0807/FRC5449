package potential_field;

import org.usfirst.frc.team5449.robot.RobotMap;

public class Goal {
	
	private double X,Y;
	private double K_Attract = RobotMap.PF_ATTRACT_CONSTANT;
	
	protected Goal(double[] Position){
		X = Position[0];
		Y = Position[1];
	}
	
	protected double[] Att_Force(double[] Position){
		double ans[] = {this.K_Attract * (X - Position[0]),this.K_Attract * (Y - Position[1])};
		return ans;
	}
	
	

}
