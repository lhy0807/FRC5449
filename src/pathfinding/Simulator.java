package pathfinding;
import java.util.ArrayList;


public class Simulator {

	public static PF_Core pf = new PF_Core();
	public static double[] Position = {1,0.8};
	
	public static double[] Coordinates = {0,0};
	public static double Angle = Math.PI/2;
	
	public static void main(String arcs[]){
		
		//obs1 = ([4.11,4.26],1.42,3.89,0)
		//obs2 = ([4.11,8.22],3.17,3.39,0)
		//obs3 = ([4.11,12.19],1.42,3.89,0)
		
		double time0 = System.currentTimeMillis();
		double time1 = System.currentTimeMillis();
		
		
		ArrayList<Integer> waypointListX = new ArrayList<Integer>();
		ArrayList<Integer> waypointListY = new ArrayList<Integer>();
		ArrayList<Integer> waypointDirection = new ArrayList<Integer>();
		ArrayList<Integer> waypointShortListX = new ArrayList<Integer>();
		ArrayList<Integer> waypointShortListY = new ArrayList<Integer>();
		
		
		PathFinding PFS = new PathFinding(100,80,400,1060,false,30,4);
		PFS.findPath(waypointListX, waypointListY, waypointDirection, false,waypointShortListX,waypointShortListY);
		time0 = System.currentTimeMillis() - time0;
		time1 = System.currentTimeMillis();
		double step = 200;
		
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
		
		//System.out.println(Math.atan2(F[1], F[0]));
		
		System.out.print(Position[0]);
		System.out.print("\t");
		System.out.println(Position[1]);
			if (pf.isReached()){
				time1 = System.currentTimeMillis() - time1;
				break;
			}
		}
		/*
		System.out.print("A* time:");
		System.out.println(time0);
		System.out.print("PF + Simulation time");
		System.out.println(time1);
		*/
		
		
		
	}
	public static double[] Rotate(double[] Vector,double Radius){
		//Positive angle means rotating counterclockwise
		//angle in radius
		double[] ans = {0,0};
		ans[0] = Math.cos(Radius) * Vector[0] - Math.sin(Radius) * Vector[1];
		ans[1] = Math.sin(Radius) * Vector[0] + Math.cos(Radius) * Vector[1];
		return ans;
	}


}
