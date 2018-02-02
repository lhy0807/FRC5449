package pathfinding;



public class PF_Core {
	
	//Obstacles:
	//obs1 = ([4.11,4.26],1.42,3.89,0)
	//obs2 = ([4.11,8.22],3.17,3.39,0)
	//obs3 = ([4.11,12.19],1.42,3.89,0)
	
	private final double MAX_PASSING_ERROR = 0.1;//meter
	
	
	private Goal goal[] = new Goal[30];
	private Obstacle obs[] = new Obstacle[15];
	
	private int obs_count = 0;
	private int goal_count = 0;
	private int current_goal = 0;
	
	private boolean is_reached = false;
	
	public PF_Core(){
		obs_count = 0;
		goal_count = 0;
		obs = new Obstacle[15];
		goal = new Goal[30];
		current_goal = 0;
		is_reached = false;
	}
	
	public void resetGoal(){
		goal = new Goal[30];
		is_reached = false;
		current_goal = 0;
		goal_count = 0;
	}
	
	public void addGoal(double[] coordinates){
		this.goal[goal_count] = new Goal(coordinates);
		goal_count += 1;
	}
	
	public void addObstacle(double[] coordinates,double range){
		this.obs[obs_count] = new Dot_obstacle(coordinates,range);
		obs_count += 1;
	}
	
	public void addObstacle(double[] coordinates,double Length,double Width,double Angle){
		this.obs[obs_count] = new Square_obstacle(coordinates ,Length ,Width ,Angle);
		obs_count += 1;
	}
	
	public double[] update(double[] Position){
		double[] ans = {0,0};
		GoalUpdate(Position);
		for (int i = 0;i < obs_count;i++){
			ans[0] += obs[i].expell_force(Position,goal[current_goal].get_Vector(Position))[0];
			ans[1] += obs[i].expell_force(Position,goal[current_goal].get_Vector(Position))[1];	
		}
		ans[0] += goal[current_goal].Att_Force(Position)[0];
		ans[1] += goal[current_goal].Att_Force(Position)[1];
		return ans;
	}
	
	public boolean isReached(){
		return this.is_reached;
	}
	
	
	private void GoalUpdate(double[] Position){
		double dis = Math.hypot((goal[current_goal].getPosition()[0] - Position[0]), (goal[current_goal].getPosition()[1] - Position[1]));
		if (dis <= this.MAX_PASSING_ERROR){
			if (current_goal == goal_count - 1){
				this.is_reached = true;
			}else{
			current_goal += 1;
			}
		}
		
	}
	
	
	
	
}
