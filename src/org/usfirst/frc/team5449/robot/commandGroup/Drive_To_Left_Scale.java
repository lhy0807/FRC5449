package org.usfirst.frc.team5449.robot.commandGroup;
import org.usfirst.frc.team5449.robot.Robot;
import org.usfirst.frc.team5449.robot.command.Drive;
import org.usfirst.frc.team5449.robot.command.DriveTo;
import org.usfirst.frc.team5449.robot.command.TurnTo;

import edu.wpi.first.wpilibj.command.CommandGroup;
import sensors.Gyro;



public class Drive_To_Left_Scale extends CommandGroup{
	public Drive_To_Left_Scale(double Range,double[] Position){
		System.out.println(Position[0]);
    	System.out.println(Position[1]);
		double[] t = {0,0},goal = {2.0,8.22},f = {0,0};

    	t[0] =  goal[0] - Position[0];
    	t[1] =  goal[1] - Position[1];
    	
    	f[0] = -t[0] / Math.hypot(t[0], t[1]) * Range;
    	f[1] = -t[1] / Math.hypot(t[0], t[1]) * Range;
    	
    	goal[0] += f[0];
    	goal[1] += f[1];
    	System.out.println(goal[0]);
    	System.out.println(goal[1]);
    	addSequential(new DriveTo(goal));
    	addSequential(new TurnTo(Math.toDegrees(-Math.atan2(t[0], t[1]))));	
	}
}
