package potential_field;

import org.usfirst.frc.team5449.robot.RobotMap;

import edu.wpi.first.wpilibj.drive.Vector2d;

public abstract class Obstacle {
	 
	protected Obstacle(){
		
	}
	
	
	protected double K_expell = RobotMap.PF_EXPELL_CONSTANT;
	protected double Range = RobotMap.PF_EXPELL_RANGE;
	
	
	
	protected abstract double[] expell_force(double[] Position);
}
