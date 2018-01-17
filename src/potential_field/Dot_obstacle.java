package potential_field;

import edu.wpi.first.wpilibj.drive.Vector2d;

public class Dot_obstacle extends Obstacle{
	
	private double diameter;//unit:meters
	
	@Deprecated 
	public Dot_obstacle(double Range){
		this.diameter = Range;
	}
	
	
	
	@Override
	protected Vector2d expell_force(Vector2d relative_vactor) {
		if (relative_vactor.magnitude() <= this.Range + this.diameter){
			//Expel force become active 
			double ForceX,ForceY;
			//Force = K * (1/relative distance - 1/maxium effect range)
			ForceX = this.K_expell * (1/relative_vactor.x - 1/(this.Range + this.diameter));
			ForceY = this.K_expell * (1/relative_vactor.y - 1/(this.Range + this.diameter));
			return new Vector2d(ForceX,ForceY);
		}else{
			//no expel force
			return new Vector2d(0,0);
		}
		
	}

}
