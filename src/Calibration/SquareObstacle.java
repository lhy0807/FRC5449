package Calibration;

public class SquareObstacle {
	private double[] Coordinates = {0,0};
	private double Length = 0;
	private double Width = 0;
	
	private static final double MAX_CALIBRATION_RANGE = 1.50;//meter
	private static final double SAFE_DISTANCE = 0.4;
	private static final double MAX_CALIBRATION_ANGLE = Math.PI / 6.0f;//Radious
	
	
	private static final double[] VECTOR_UP= {0,1};
	private static final double[] VECTOR_DOWN = {0,-1};
	private static final double[] VECTOR_LEFT = {-1,0};
	private static final double[] VECTOR_RIGHT = {1,0};
	
	
	//these angles point inward
	private static final double ANGLE_UP= Math.PI;
	private static final double ANGLE_DOWN = 0;
	private static final double ANGLE_LEFT = Math.PI * 0.5;
	private static final double ANGLE_RIGHT = -Math.PI * 0.5;
	
	
	
	
	protected SquareObstacle(double[] Coordinates,double Length,double Width){
		this.Coordinates = Coordinates;
		this.Length = Length;
		this.Width = Width;
	}
	
	//[UpEdge,DownEdge,LeftEdge,RightEdge]
	protected boolean[] is_calibratable(double[] Position,double Heading,CB_ultrasonic ultrasonic){
		
		double[] us_vector = ultrasonic.getPos();
		us_vector = Rotate(us_vector,-Heading);
		
		System.out.print("US_VECTOR_X:");
		System.out.println(us_vector[0]);
		System.out.print("US_VECTOR_Y:");
		System.out.println(us_vector[1]);
		
		double[] p = {Position[0] + us_vector[0] - Coordinates[0],Position[1] + us_vector[1] - Coordinates[1]};
		

		
		double heading = relative_angle(Heading,-ultrasonic.getHeading());//adds the angle

		
		
		
		
		boolean[] val = {false,false,false,false};
		System.out.println(heading);
		if(((Math.abs((relative_angle(ANGLE_UP,heading))) <  MAX_CALIBRATION_ANGLE)&& (Dot(p,VECTOR_UP) > Length*0.5) && (Dot(p,VECTOR_UP) < Length*0.5 + MAX_CALIBRATION_RANGE) && (Math.abs(Dot(VECTOR_RIGHT,p)) < Width * 0.5 - SAFE_DISTANCE))){
			val[0] = true;//UP
			System.out.println("UP");
			return val;
		}else if((Math.abs((relative_angle(ANGLE_DOWN,heading))) <  MAX_CALIBRATION_ANGLE)&& (Dot(p,VECTOR_DOWN) > Length*0.5) && (Dot(p,VECTOR_DOWN) < Length*0.5 + MAX_CALIBRATION_RANGE) && (Math.abs(Dot(VECTOR_RIGHT,p)) < Width * 0.5 - SAFE_DISTANCE)){
			val[1] = true;//DOWN
			System.out.println("DOWN");
			return val;
		}else if((Math.abs((relative_angle(ANGLE_LEFT,heading))) <  MAX_CALIBRATION_ANGLE)&& (Dot(p,VECTOR_LEFT) > Width*0.5) && (Dot(p,VECTOR_LEFT) < Width*0.5 + MAX_CALIBRATION_RANGE) && (Math.abs(Dot(VECTOR_UP,p)) < Length * 0.5 - SAFE_DISTANCE)){
			val[2] = true;//LEFT
			System.out.println("LEFT");
			return val;
		}else if ((Math.abs((relative_angle(ANGLE_RIGHT,heading))) <  MAX_CALIBRATION_ANGLE)&& (Dot(p,VECTOR_RIGHT) > Width*0.5) && (Dot(p,VECTOR_RIGHT) < Width*0.5 + MAX_CALIBRATION_RANGE) && (Math.abs(Dot(VECTOR_UP,p)) < Length * 0.5 - SAFE_DISTANCE)){
			val[3] = true;//RIGHT
			System.out.println("RIGHT");
			return val;
		}else{
			System.out.println("NONE");
			return val;//NONE
		}
	}
	
	protected double[] calibrate(double[] Position,double Heading,boolean[] is_calibratable,CB_ultrasonic ultrasonic){
		double[] val = {0,0};
		double USheading = relative_angle(Heading,-ultrasonic.getHeading());
		double K;	
		if (is_calibratable[0]){
			//UP
			K = Math.cos(relative_angle(ANGLE_UP,USheading));
			val[1] = (ultrasonic.get() * K + Coordinates[1] + Length * 0.5) - Position[1];
			
		}else if(is_calibratable[1]){
			//DOWN
			K = Math.cos(relative_angle(ANGLE_DOWN,USheading));
			val[1] = (-ultrasonic.get() * K + Coordinates[1] -Length * 0.5) - Position[1];
		}else if(is_calibratable[2]){
			//LEFT
			K = Math.cos(relative_angle(ANGLE_LEFT,USheading));
			val[0] = (Coordinates[0] - Width * 0.5 - ultrasonic.get() * K) - Position[0];
		}else if(is_calibratable[3]){
			//RIGHT
			K = Math.cos(relative_angle(ANGLE_RIGHT,USheading));
			val[0] = (Coordinates[0] + Width * 0.5 + ultrasonic.get() * K) - Position[0];
		}else{
			//NONE
		}
		return val;
	}
	
	
	
	
	public double relative_angle(double angle1,double angle2){
		double val = angle1 - angle2;
		if (val > Math.PI){
			val -= Math.PI * 2;
		}
		if (val <= -Math.PI){
			val += Math.PI * 2;
		}
		return val;
	}
	
	public double[] Rotate(double[] Vector,double Radius){
		//Positive angle means rotating counterclockwise
		//angle in radius
		//tested
		double[] ans = {0,0};
		ans[0] = Math.cos(Radius) * Vector[0] - Math.sin(Radius) * Vector[1];
		ans[1] = Math.sin(Radius) * Vector[0] + Math.cos(Radius) * Vector[1];
		return ans;
	}
	
	public static double Dot(double[] Vector1,double[] Vector2){
		return Vector1[0] * Vector2[0] + Vector1[1] * Vector2[1];
	}
	
	public static double[] add(double[] vector1,double[] vector2){
		double[] val = {0,0};
		val[0] = vector1[0] + vector2[0];
		val[1] = vector1[1] + vector2[1];
		return val;
	}
	
}
