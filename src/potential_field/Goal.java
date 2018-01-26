package potential_field;


public class Goal {
	
	private double X,Y;
	private double K_Attract = 1;
	
	protected Goal(double[] Position){
		X = Position[0];
		Y = Position[1];
	}
	
	protected double[] Att_Force(double[] Position){
		double ans[] = {this.K_Attract * (X - Position[0]),this.K_Attract * (Y - Position[1])};
		return ans;
	}
	
	

}
