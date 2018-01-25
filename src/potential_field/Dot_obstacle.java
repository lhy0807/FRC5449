package potential_field;

import edu.wpi.first.wpilibj.drive.Vector2d;

public class Dot_obstacle extends Obstacle{
	
	private double diameter;//unit:meters
	private double[] Coordinates = {0,0};
	
	

	public Dot_obstacle(double[] Coordinates,double Range){
		this.diameter = Range;
		this.Coordinates = Coordinates;
	}
	
	
	
	@Override
	protected double[] expell_force(double[] Position) {
		double ans[] = {0,0};
		if (Math.hypot(Position[0], Position[1]) <= this.Range + this.diameter){
			//Expel force become active 
			//Force = K * (1/relative distance - 1/maxium effect range)
			ans[0] = this.K_expell * (1/(Coordinates[0] - Position[0]) - 1/(this.Range + this.diameter));
			ans[1] = this.K_expell * (1/(Coordinates[1] - Position[1]) - 1/(this.Range + this.diameter));
			return ans;
		}else{
			//no expel force
			return ans;
		}
	}

}
