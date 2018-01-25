package potential_field;



public class PF_Core {
	
	private Goal goal;
	private Obstacle obs[] = new Obstacle[15]; 
	private int obs_count = 0;
	@Deprecated
	public PF_Core(){
		obs_count = 0;
		obs = new Obstacle[15];
	}
	
	public void setGoal(double[] coordinates){
		this.goal = new Goal(coordinates);
	}
	
	public void addObstacle(double[] coordinates,double range){
		this.obs[obs_count] = new Dot_obstacle(coordinates,range);
	}
	
	public double[] get(double[] Position){
		double[] ans = {0,0};
		for (int i = 0;i < obs_count+1;i++){
			ans[0] += obs[i].expell_force(Position)[0];
			ans[1] += obs[i].expell_force(Position)[1];
		}
		ans[0] += goal.Att_Force(Position)[0];
		ans[1] += goal.Att_Force(Position)[1];
		return ans;
	}
	
	
	
	
}