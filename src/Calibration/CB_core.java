package Calibration;

import sensors.AnalogUltraSonic;

public class CB_core {
	
	private CB_ultrasonic[] ultrasonic;
	private SquareObstacle[] obstacles;
	private int us_count = 0;
	private int obstacle_count = 0;
	private double[] X_corrections = new double[5];
	private int X_count = 0;
	private double[] Y_corrections = new double[5];
	private int Y_count = 0;
	private double X_corrections_time = 0;
	private double Y_corrections_time = 0;
	
	
	
	public CB_core(){
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
	
	public void loadFRCfield(){
		double[] switch1 = {4.11,4.26};
		add_obstacle(switch1,1.42,3.89);
		double[] switch2 = {4.11,12.19};
		add_obstacle(switch2,1.42,3.89);
		double[] wall_left = {0,8.22};
		add_obstacle(wall_left,0.05,16.44);
		double[] wall_right = {8.22,8.22};
		add_obstacle(wall_right,0.05,16.44);
		double[] wall_down = {4.11,0};
		add_obstacle(wall_down,8.22,0.05);
		double[] wall_up = {4.11,16.22};
		add_obstacle(wall_up,8.22,0.05);	
	}
	
	public double[] Update(double[] Position,double Heading,double time){
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
			
			if (time - X_corrections_time > 0.5){
				X_count = 0;
				X_corrections_time = time;
			}
			
			X_corrections[X_count] = calibrationX;
			X_count ++;
			if (X_count >= 5){
				X_count = 0;
				BubbleSort(X_corrections);
				
				final_calibration[0] = (X_corrections[1] + X_corrections[2] + X_corrections[3]) * 0.333f;

			}
		}
		
		if (vaild_calibrationY != 0){
			calibrationY /= vaild_calibrationY;
			
			if (time - Y_corrections_time > 0.5){
				Y_count = 0;
				Y_corrections_time = time;
			}
			
			
			Y_corrections[Y_count] = calibrationY;
			Y_count ++;
			if (Y_count >= 5){
				Y_count = 0;
				BubbleSort(Y_corrections);
				
				final_calibration[1] = (Y_corrections[1] + Y_corrections[2] + Y_corrections[3]) * 0.333f;

			}

			
		}
		return final_calibration;
	}
	
	private void BubbleSort(double [] arr){

	     double temp;//临时变量
	     for(int i=0; i<arr.length-1; i++){   //表示趟数，一共arr.length-1次。
	         for(int j=arr.length-1; j>i; j--){

	             if(arr[j] < arr[j-1]){
	                 temp = arr[j];
	                 arr[j] = arr[j-1];
	                 arr[j-1] = temp;
	             }
	         }
	     }
	 }
	
	
	
	
}
