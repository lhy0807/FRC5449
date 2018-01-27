package potential_field;



public abstract class Obstacle {
	 
	protected Obstacle(){
		
	}
	
	
	protected double K_expell = 0.5;
	protected double Range = 0.6;
	
	
	
	protected abstract double[] expell_force(double[] Position,double[] VectorToGoal);

}
