package potential_field;


public class Goal {
	
	private double X,Y;
	private double K_Attract = 3;
	
	public Goal(double[] Position){
		X = Position[0];
		Y = Position[1];
	}
	
	public double[] Att_Force(double[] Position){
		double ans[] = {this.K_Attract * (X - Position[0]),this.K_Attract * (Y - Position[1])};
		return ans;
	}
	
	public double[] get_Vector(double[] Position){
		double[] ans = {0,0};
		ans[0] = this.X - Position[0];
		ans[1] = this.Y - Position[1];
		return ans;
	}
	

}
