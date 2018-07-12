package pathfinding;
import java.util.ArrayList;


public class Simulator {

	private PF_Core pf;
	private PF_Core pf2;
	private PathFinding PFS;
	private double[] Position = {1,0.8};
	private double step = 10;
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
		pf2 = new PF_Core();
		PFS = new PathFinding((int)(StartPos[0]*100),(int)(StartPos[1]*100),(int)(EndPos[0]*100),(int)(EndPos[1]*100),false,5,1);
	
	}
	public double[][] Simulate2(){
		
		PFS.findPath(waypointListX, waypointListY, waypointDirection, false,waypointShortListX,waypointShortListY);
		
		int count = waypointShortListX.size();
		double[][] val = new double[count-1][2];
		for (int i = 0; i < count - 1; i++){
			val[i][0] = waypointShortListX.get(i+1) * 0.01d;
			val[i][1] = waypointShortListY.get(i+1) * 0.01d;			
		}
		 
		return val;
	}
	
	
	public boolean Simulate(){
		PFS.findPath(waypointListX, waypointListY, waypointDirection, false,waypointShortListX,waypointShortListY);

		int waypoint_count = 0,interval = 0;
		double sum = 0;
		for(int i=1; i<waypointShortListX.size();i++){
			sum += Math.hypot((waypointShortListX.get(i) - waypointShortListX.get(i-1)),(waypointShortListY.get(i)-waypointShortListY.get(i-1)));
			double[] val = {waypointShortListX.get(i)*0.01,waypointShortListY.get(i)*0.01};
			pf.addGoal(val);
			pf2.addGoal(val);
			System.out.print(waypointShortListX.get(i));
			System.out.print('\t');
			System.out.println(waypointShortListY.get(i));
		}
		
		
		//Switch,Scale,Switch
		double[] pos1 = {1.20,0.09};
		double[] pos2 = {0.30,1.20};
		double[] pos3 = {1.52,1.54};
		//field frame
		double[] posleft = {0,1.2};
		double[] posright = {1.8,1.2};
		double[] posup = {0.9,2.4};
		double[] posdown = {0.9,0};

		//obstacles
		pf.addObstacle(pos1, 0.18,0.18,0);
		pf.addObstacle(pos2, 0.12,0.60,0);
		pf.addObstacle(pos3, 0.18,0.18,0);
		//frames
		pf.addObstacle(posleft, 2.4,0.02,0);
		pf.addObstacle(posright, 2.4,0.05,0);
		pf.addObstacle(posup, 0.05,1.8,0);
		pf.addObstacle(posdown, 0.05,1.8,0);
		
		//obstacles
		pf2.addObstacle(pos1, 0.18,0.18,0);
		pf2.addObstacle(pos2, 0.12,0.60,0);
		pf2.addObstacle(pos3, 0.18,0.18,0);
		//frames
		pf2.addObstacle(posleft, 2.4,0.02,0);
		pf2.addObstacle(posright, 2.4,0.05,0);
		pf2.addObstacle(posup, 0.05,1.8,0);
		pf2.addObstacle(posdown, 0.05,1.8,0);
				
		
		for (int i = 0; i < 500; i++){
		
		double[] F = {0,0};
			
		F = pf.update(Position);			
		Position[0] += F[0] / Math.hypot(F[0], F[1]) * 0.05;
		Position[1] += F[1] / Math.hypot(F[0], F[1]) * 0.05;
		
		System.out.print(Position[0]);
		System.out.print("\t");
		System.out.println(Position[1]);
			if (pf.isReached()){
				break;
			}
		}
		
		is_simulated = true;
		boolean reached = pf.isReached();
		return reached;
		

	}
	
	public double[] getForce(double[] Position){
		double[] val = {0,0};
		if (this.is_simulated){
		val = this.pf2.update(Position);
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
