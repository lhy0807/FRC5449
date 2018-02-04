package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class RecvGamedata extends Command{

	private String gamedata;
	

	@Override
	protected void initialize() {
		this.gamedata = DriverStation.getInstance().getGameSpecificMessage();
	}
	
	@Override
	protected void execute() {
		this.gamedata = DriverStation.getInstance().getGameSpecificMessage();
	}
	
	@Override
	protected boolean isFinished() {
		return this.gamedata != null;
	}
	
	@Override
	protected void end() {
		
		Robot.Game_data[0] = gamedata.charAt(0) == 'R';
		Robot.Game_data[1] = gamedata.charAt(1) == 'R';
		Robot.Game_data[2] = gamedata.charAt(2) == 'R';

	}
	
}
