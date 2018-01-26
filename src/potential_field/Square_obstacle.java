package potential_field;

public class Square_obstacle extends Obstacle{
	
	private double Angle;
	private double Width;
	private double Length;
	private double K_exp = this.K_expell;
	private double range = this.Range;
	private double[] Coordinates;
	
	public Square_obstacle(double[] Coordinates ,double Length ,double Width ,double Angle){
		this.Length = Length;
		this.Width = Width;
		this.Angle = Angle;//counter clockwise
		this.Coordinates = Coordinates;
	}


	@Override
	public double[] expell_force(double[] Position) {
		
		double[] nx = {1,0};//Unit vector in X direction
		double[] ny = {0,1};//Unit vector in Y direction
		double[] p = {Coordinates[0] - Position[0],Coordinates[1] - Position[1]};//relative position vector 
		double p_mag = Math.hypot(p[0], p[1]);
		double px = 0,py = 0;//the position vector in X and Y direction
		nx = Rotate(nx,Angle);
		ny = Rotate(ny,Angle);
		
		px = Dot(p,nx);
		py = Dot(p,ny);
		
		double[] ans = {0,0};
		double Force;
		
		if (Math.abs(px) <= this.Width/2){
			if (Math.abs(py) <= this.Length/2){
				return ans;//Inside the box, return 0
			}else{
				Force = K_exp * (1/(Math.abs(py) - Length / 2) - 1/(range + Length / 2));
				Force = Math.signum(py) * Force;
				ans[0] = -Math.cos(Angle + Math.PI/2) * Force;
				ans[1] = -Math.sin(Angle + Math.PI/2) * Force;
				return ans;
			}
		} else{ 
			if (Math.abs(py) <= this.Length/2){
				Force = K_exp * (1/(Math.abs(px) - Width / 2) - 1/(range + Width / 2));
				Force = Math.signum(px) * Force;
				ans[0] = -Math.cos(Angle) * Force;
				ans[1] = -Math.sin(Angle) * Force;
				return ans;
			}else{
				double[] pos = {0,0};
				pos[0] = -Math.signum(px) * Width/2;
				pos[1] = -Math.signum(py) * Length/2;
				
				pos = Rotate(pos,Angle);
				pos[0] += Coordinates[0];
				pos[1] += Coordinates[1];

				ans = new Dot_obstacle(pos,0).expell_force(Position);
				return ans;
			}
		}
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
	
	public double Dot(double[] Vector1,double[] Vector2){
		return Vector1[0] * Vector2[0] + Vector1[1] * Vector2[1];
	}
	
}
