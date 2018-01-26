package potential_field;



public class Dot_obstacle extends Obstacle{
	
	private double radious;//unit:meters
	private double[] Coordinates = {0,0};
	
	public Dot_obstacle(double[] Coordinates,double Radious){
		this.radious = Radious;
		this.Coordinates = Coordinates;
	}
	@Override
	protected double[] expell_force(double[] Position) {
		double ans[] = {0,0};
		if (Math.hypot(Position[0] - Coordinates[0], Position[1] - Coordinates[1]) <= this.Range + this.radious){
			//Expel force become active 
			//Force = K * (1/relative distance - 1/maxium effect range)
			double Force =K_expell *( 1/(Math.hypot(Position[0] - Coordinates[0], Position[1] - Coordinates[1]) - this.radious)- 1/(this.Range) );
			double theta = Math.atan2(Coordinates[1] - Position[1], Coordinates[0] - Position[0]);
			theta += Math.PI;
			ans[0] = Force * Math.cos(theta);
			ans[1] = Force * Math.sin(theta);
			return ans;
		}else{
			//no expel force
			return ans;
		}
	}
}
