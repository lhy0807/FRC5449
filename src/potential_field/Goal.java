package potential_field;


public class Goal {
	
	private double X,Y;
	private double K_Attract = 2;
	
	public Goal(double[] Position){
		X = Position[0];
		Y = Position[1];
	}
	
	public double[] Att_Force(double[] Position){
		double n = 0.5;
		double ans[] = {Math.signum((X - Position[0]))*this.K_Attract * Math.pow(Math.abs((X - Position[0])),n),this.K_Attract *Math.signum((Y - Position[1]))* Math.pow(Math.abs(Y - Position[1]),n)};
		return ans;
	}
	
	public double[] get_Vector(double[] Position){
		double[] ans = {0,0};
		ans[0] = this.X - Position[0];
		ans[1] = this.Y - Position[1];

		return ans;
	}
	

}
