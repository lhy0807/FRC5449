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
	public double[] expell_force(double[] Position,double[] VectorToGoal) {
		Dot_obstacle obs;
		double[] nx = {1,0};//Unit vector in X direction
		double[] ny = {0,1};//Unit vector in Y direction
		double[] p = {Coordinates[0] - Position[0],Coordinates[1] - Position[1]};//relative position vector 
		double p_mag = Math.hypot(p[0], p[1]);
		double px = 0,py = 0;//the position vector in X and Y direction
		

		
		px = Dot(p,nx);
		py = Dot(p,ny);
		
		double gx = Dot(VectorToGoal,nx);
		double gy = Dot(VectorToGoal,ny);
		
		
		double[] ans = {0,0};
		
		if (Math.abs(px) <= this.Width/2){
			if (Math.abs(py) <= this.Length/2){
				return ans;//Inside the box, return 0
			}else{	
				double[] coord = {-px + Coordinates[0],-Math.signum(py)*Length/2+Coordinates[1]};
				/*
				System.out.println("DOT COORD:");
				System.out.print(coord[0]);
				System.out.print("\t");
				System.out.println(coord[1]);
				*/
				return new Dot_obstacle(coord,0).expell_force(Position, VectorToGoal);
			}
		} else{ 
			if (Math.abs(py) <= this.Length/2){
				double[] coord = {-Math.signum(px)*Width/2 + Coordinates[0],-py + Coordinates[1]};

				/*
				System.out.println("DOT COORD:");
				System.out.print(coord[0]);
				System.out.print("\t");
				System.out.println(coord[1]);
				*/
				return new Dot_obstacle(coord,0).expell_force(Position, VectorToGoal);
			}else{
				double[] pos = {0,0};
				pos[0] = -Math.signum(px) * Width/2;
				pos[1] = -Math.signum(py) * Length/2;
				
				pos = Rotate(pos,Angle);
				pos[0] += Coordinates[0];
				pos[1] += Coordinates[1];

				ans = new Dot_obstacle(pos,0).expell_force(Position,VectorToGoal);
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
