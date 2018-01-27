package potential_field;



public class Dot_obstacle extends Obstacle{
	
	private double radious;//unit:meters
	private double[] Coordinates = {0,0};
	
	public Dot_obstacle(double[] Coordinates,double Radious){
		this.radious = Radious;
		this.Coordinates = Coordinates;
	}
	
	@Override
	protected double[] expell_force(double[] Position,double[] VectorToGoal) {
		double[] Force = {0,0};
		double n = 1;
		double R0 = this.radious;//Maximum affecting range
		
		double[] V_OR = {0,0};//Obstacle -> Robot
		V_OR[0] = Position[0] - Coordinates[0];
		V_OR[1] = Position[1] - Coordinates[1];
		
		double[] V_RG = {0,0};//Robot -> Goal
		V_RG[0] = VectorToGoal[0];
		V_RG[1] = VectorToGoal[1];
		
		double p_OR = Math.hypot(V_OR[0], V_OR[1]);//distance between obstacle and robot
		
		double p_RG = Math.hypot(V_RG[0], V_RG[1]);//distance between robot and goal
		
		if (p_OR <= radious){	
			
		double[] Vn_OR = {0,0};//Unit vector(obstacle to robot)
		Vn_OR[0] = V_OR[0] / p_OR;
		Vn_OR[1] = V_OR[1] / p_OR;
		
		double[] Vn_RG = {0,0};//Unit vector(robot to goal)
		Vn_RG[0] = V_RG[0] / p_RG;
		Vn_RG[1] = V_RG[1] / p_RG;
		
		double F_rep1;//Force1, same direction with Vn_OR(obstacle to robot) 
		F_rep1 = K_expell * (1/p_OR - 1/R0) * Math.pow(p_RG, n) * Math.pow(p_OR, -2);
		
		double F_rep2;//Force2, same direction with Vn_RG(Robot to Goal)
		F_rep2 = 0.5 * n * K_expell * Math.pow((1/p_OR - 1/R0),2) * Math.pow(p_RG, n-1);
		
		Force[0] =  Vn_OR[0] * F_rep1 + Vn_RG[0] * F_rep2;
		Force[1] =  Vn_OR[1] * F_rep1 + Vn_RG[1] * F_rep2;
		}
		
		
		return Force;
	}
	
	public boolean is_In(double[] Position,double[] VectorToGoal) {
		
		double[] V_OR = {0,0};//Obstacle -> Robot
		V_OR[0] = Position[0] - Coordinates[0];
		V_OR[1] = Position[1] - Coordinates[1];

		double p_OR = Math.hypot(V_OR[0], V_OR[1]);//distance between obstacle and robot

		return p_OR <= radious;
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
