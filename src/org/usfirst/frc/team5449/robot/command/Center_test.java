package org.usfirst.frc.team5449.robot.command;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import sensors.Gyro;

public class Center_test extends Command{
		private Timer timer = new Timer();
		public Center_test(){
			requires(Robot.chassis);
		}
		protected void initialize() {
			this.timer.reset();
			System.out.println("===Center Test Begins===");
			this.timer.start();
		}

		// Called repeatedly when this Command is scheduled to run
		protected void execute() {
			double[] Datas = {0,0,0};
			
			Datas[0] = Robot.e1.read()[1];
			Datas[1] = Gyro.getAngle();
			Datas[2] = this.timer.get();
			
			for (int i = 0; i < 2;i ++){
				System.out.print(Datas[i]);
				System.out.print("\t");
			}
			System.out.println(Datas[2]);
			Robot.chassis.arcade_drive(0, Robot.oi.stick1.getRawAxis(2));
			
		}

		// Make this return true when this Command no longer needs to run execute()
		protected boolean isFinished() {
			return timer.get() > 30;
		}

		// Called once after isFinished returns true
		protected void end() {
		}

		// Called when another command which requires one or more of the same
		// subsystems is scheduled to run
		protected void interrupted() {
		
		}
 }


