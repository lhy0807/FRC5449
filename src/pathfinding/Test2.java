package pathfinding;

public class Test2 {
	public static void main(String[] args){
		//How to use it
		
		double[] start = {0.3,0.3};
		double[] end = {1.55,2.15};
		Simulator simulator = new Simulator(start,end,1);
		simulator.Simulate();

		
	}
}
