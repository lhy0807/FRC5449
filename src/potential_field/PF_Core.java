package potential_field;



public class PF_Core {
	
	//Obstacles:
	//obs1 = ([4.11,4.26],1.42,3.89,0)
	//obs2 = ([4.11,8.22],3.17,3.39,0)
	//obs3 = ([4.11,12.19],1.42,3.89,0)
	
	
	
	
	private Goal goal;
	private Obstacle obs[] = new Obstacle[15]; 
	private int obs_count = 0;
	
	
	public PF_Core(){
		obs_count = 0;
		obs = new Obstacle[15];
	}
	
	public void setGoal(double[] coordinates){
		this.goal = new Goal(coordinates);
	}
	
	public void addObstacle(double[] coordinates,double range){
		this.obs[obs_count] = new Dot_obstacle(coordinates,range);
		obs_count += 1;
	}
	
	public void addObstacle(double[] coordinates,double Length,double Width,double Angle){
		this.obs[obs_count] = new Square_obstacle(coordinates ,Length ,Width ,Angle);
		obs_count += 1;
	}
	
	public double[] get(double[] Position){
		double[] ans = {0,0};
		for (int i = 0;i < obs_count;i++){
			ans[0] += obs[i].expell_force(Position,goal.get_Vector(Position))[0];
			ans[1] += obs[i].expell_force(Position,goal.get_Vector(Position))[1];
			/*
			System.out.println("OBSTACLE:" + i);
			System.out.print("X:");
			System.out.print(ans[0]);
			System.out.print("Y:");
			System.out.println(ans[1]);
			*/
			
			
			
		}
		ans[0] += goal.Att_Force(Position)[0];
		ans[1] += goal.Att_Force(Position)[1];
		return ans;
	}
	
	
	
	
}
