package pathfinding;
import java.util.ArrayList;


public class Simulator {

	private PF_Core pf;
	private PathFinding PFS;
	private double[] Position = {1,0.8};
	private double step = 100;
	private boolean is_simulated = false;
	
	private ArrayList<Integer> waypointListX = new ArrayList<Integer>();
	private ArrayList<Integer> waypointListY = new ArrayList<Integer>();
	private ArrayList<Integer> waypointDirection = new ArrayList<Integer>();
	private ArrayList<Integer> waypointShortListX = new ArrayList<Integer>();
	private ArrayList<Integer> waypointShortListY = new ArrayList<Integer>();
	
	public Simulator(double[] StartPos,double[] EndPos,double step){
		is_simulated = false;
		step *= 100;//convert m -> cm
		this.step = step;
		Position[0] = StartPos[0];
		Position[1] = StartPos[1];
		//initialize
		waypointListX = new ArrayList<Integer>();
		waypointListY = new ArrayList<Integer>();
		waypointDirection = new ArrayList<Integer>();
		waypointShortListX = new ArrayList<Integer>();
		waypointShortListY = new ArrayList<Integer>();
		pf = new PF_Core();
		PFS = new PathFinding((int)(StartPos[0]*100),(int)(StartPos[1]*100),(int)(EndPos[0]*100),(int)(EndPos[1]*100),false,30,8);
	
	}
	
	public boolean Simulate(){
		PFS.findPath(waypointListX, waypointListY, waypointDirection, false,waypointShortListX,waypointShortListY);

		int waypoint_count = 0,interval = 0;
		double sum = 0;
		for(int i=1; i<waypointShortListX.size();i++){
			sum += Math.hypot((waypointShortListX.get(i) - waypointShortListX.get(i-1)),(waypointShortListY.get(i)-waypointShortListY.get(i-1)));
		}
		waypoint_count = (int) Math.ceil((sum / step));
		double[] waypointX = new double[waypoint_count];
		double[] waypointY = new double[waypoint_count];
		
		interval = (int)Math.floor((waypointListX.size()-1) / (waypoint_count -1));
		for (int i = 0;i < waypoint_count -1;i++){
			waypointX[i] = waypointListX.get(i * interval);
			waypointY[i] = waypointListY.get(i * interval);
		}
			waypointX[waypoint_count-1] = waypointShortListX.get(waypointShortListX.size()-1);
			waypointY[waypoint_count-1] = waypointShortListY.get(waypointShortListY.size()-1);
		
			for (int i = 0; i < waypoint_count; i++){
				waypointX[i] /= 100;
				waypointY[i] /= 100;
				double[] val = {waypointX[i],waypointY[i]};
				pf.addGoal(val);
			}
		
		//Switch,Scale,Switch
		double[] pos1 = {4.11,4.26};
		double[] pos2 = {4.11,8.22};
		double[] pos3 = {4.11,12.19};
		//field frame
		double[] posleft = {0,8.22};
		double[] posright = {8.22,8.22};
		double[] posup = {4.11,16.44};
		double[] posdown = {4.11,0};

		//obstacles
		pf.addObstacle(pos1, 1.42,3.89,0);
		pf.addObstacle(pos2, 3.17,3.39,0);
		pf.addObstacle(pos3, 1.42,3.89,0);
		//frames
		pf.addObstacle(posleft, 16.44,0.05,0);
		pf.addObstacle(posright, 16.44,0.05,0);
		pf.addObstacle(posup, 0.05,8.22,0);
		pf.addObstacle(posdown, 0.05,8.22,0);
				
		for (int i = 0; i < 500; i++){
		
		double[] F = {0,0};
			
		F = pf.update(Position);			
		Position[0] += F[0] / Math.hypot(F[0], F[1]) * 0.05;
		Position[1] += F[1] / Math.hypot(F[0], F[1]) * 0.05;
			if (pf.isReached()){
				break;
			}
		}
		
		is_simulated = true;
		boolean reached = pf.isReached();
		
		pf.resetGoal();
		for (int i = 0; i < waypoint_count; i++){
			waypointX[i] /= 100;
			waypointY[i] /= 100;
			double[] val = {waypointX[i],waypointY[i]};
			pf.addGoal(val);
		}
		return reached;
	}
	
	public double[] getForce(double[] Position){
		double[] val = {0,0};
		if (this.is_simulated){
		val = this.pf.update(Position);
		}
		return val;
	}
	
	
	public static double[] Rotate(double[] Vector,double Radius){
		double[] ans = {0,0};
		ans[0] = Math.cos(Radius) * Vector[0] - Math.sin(Radius) * Vector[1];
		ans[1] = Math.sin(Radius) * Vector[0] + Math.cos(Radius) * Vector[1];
		return ans;
	}


}
