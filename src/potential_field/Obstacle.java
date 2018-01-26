package potential_field;



public abstract class Obstacle {
	 
	protected Obstacle(){
		
	}
	
	
	protected double K_expell = 2;
	protected double Range = 2;
	
	
	
	protected abstract double[] expell_force(double[] Position,double[] VectorToGoal);

}
