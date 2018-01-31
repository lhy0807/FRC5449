package Calibration;

import sensors.AnalogUltraSonic;

public class CB_core {
	
	private CB_ultrasonic[] ultrasonic;
	private SquareObstacle[] obstacles;
	private int us_count = 0;
	private int obstacle_count = 0;
	
	
	CB_core(){
		ultrasonic = new CB_ultrasonic[5];
		obstacles = new SquareObstacle[10];
		us_count = 0;
		obstacle_count = 0;
	}
	
	public void add_obstacle(double[] Coordinates,double Length,double Width){
		obstacles[obstacle_count] = new SquareObstacle(Coordinates,Length,Width);
		obstacle_count += 1;
	}
	
	public void add_sensor(double[] Coordinates,double Heading,AnalogUltraSonic A_ultrasonic){
		ultrasonic[us_count] = new CB_ultrasonic(Coordinates,Heading,A_ultrasonic);
		us_count += 1;
	}
	
	public double[] Update(double[] Position,double Heading){
		double[] final_calibration = {0,0};
		int vaild_calibrationX = 0,vaild_calibrationY = 0;
		double calibrationX = 0,calibrationY = 0;
		
		for (int i = 0;i < obstacle_count;i++){
			for (int j = 0;j < us_count;j++){
				//Calculate obstacle[i] and ultrasonic[j] 
				boolean[] is_calibratable = obstacles[i].is_calibratable(Position, Heading, ultrasonic[j]);
				double[] calibration = obstacles[i].calibrate(Position, Heading, is_calibratable, ultrasonic[j]);
				if (is_calibratable[0] || is_calibratable[1]){
					calibrationY += calibration[1];
					vaild_calibrationY += 1;
				}else if (is_calibratable[2] || is_calibratable[3]){
					calibrationX += calibration[0];
					vaild_calibrationX += 1;
				}
			}
		}
		
		if (vaild_calibrationX != 0){
		calibrationX /= vaild_calibrationX;
		}
		
		if (vaild_calibrationY != 0){
		calibrationY /= vaild_calibrationY;
		}
		
		final_calibration[0] = calibrationX;
		final_calibration[1] = calibrationY;
		
		return final_calibration;
	}
	
	
	
	
}
