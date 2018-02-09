package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn;
import org.usfirst.frc.team5449.robot.command.IntakeIn2;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterStop;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.LifterToUp;
import org.usfirst.frc.team5449.robot.command.Lifterbump;
import org.usfirst.frc.team5449.robot.command.Release_Cube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScaleRelease extends CommandGroup{
	

	public ScaleRelease(){
		addSequential(new IntakeOut());
        addSequential(new IntakeStop());
        addSequential(new Delay(0.7));
		addParallel(new LifterToUp());
		addSequential(new Delay(0.7));
        addSequential(new DriveDistance(1,0.6));
        addSequential(new Release_Cube());
        addSequential(new DriveDistance(-0.5,1));   
        addSequential(new LifterToDown());
	}

	public ScaleRelease(double dis){
		addSequential(new IntakeOut());
        addSequential(new IntakeStop());
        addSequential(new Delay(0.7));
		addParallel(new LifterToUp());
		addSequential(new Delay(0.7));
        addSequential(new DriveDistance(dis,1.2));
        addSequential(new Release_Cube());
        addSequential(new DriveDistance(-0.5,1));   
        addSequential(new LifterToDown());
	}
}
