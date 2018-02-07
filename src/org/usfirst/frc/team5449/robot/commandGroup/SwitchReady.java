package org.usfirst.frc.team5449.robot.commandGroup;

import org.usfirst.frc.team5449.robot.command.Delay;
import org.usfirst.frc.team5449.robot.command.DriveDistance;
import org.usfirst.frc.team5449.robot.command.IntakeIn2;
import org.usfirst.frc.team5449.robot.command.IntakeOut;
import org.usfirst.frc.team5449.robot.command.IntakeStop;
import org.usfirst.frc.team5449.robot.command.LifterToDown;
import org.usfirst.frc.team5449.robot.command.LifterToMid;
import org.usfirst.frc.team5449.robot.command.Release_Cube;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SwitchReady extends CommandGroup{
	public SwitchReady(){
		addSequential(new IntakeOut());
        addSequential(new IntakeStop()); 
        addSequential(new Delay(0.4));
        addParallel(new LifterToMid());
        addSequential(new Delay(0.4));
		addSequential(new IntakeIn2());
        addSequential(new IntakeStop());

	}
}
