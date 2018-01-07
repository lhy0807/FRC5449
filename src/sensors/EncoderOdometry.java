package sensors;

import edu.wpi.first.wpilibj.Encoder;

public class EncoderOdometry {
	
	private Encoder LeftEncoder;
	private Encoder RightEncoder;
	private double x;
	private double y;
	private double r;
	private final double Width = 0.5; //TODO in METERS, measure the length and change this 
	
	@Deprecated //TODO Parameters Unfinished!
	public EncoderOdometry(){
		//Encoder Support
		LeftEncoder = new Encoder(1,2);//TODO put this into RoboMap
		RightEncoder = new Encoder(3,4);
		x = 0.0;
		y = 0.0;
		r = 0.0; //hu du zhi! zheng Y fang xiang yu che chao xiang de jia jiao
	}
	
	/**DIRECTLY UPDATES THE POSITION OF THE ROBOT, SHOULD BE EXECUTED EACH LOOP*/
	public void UpdatePosition(){
		//descriptions see calculation paper
		double leftDis = FlushRead(LeftEncoder);
		double rightDis = FlushRead(RightEncoder);
		double temp = ((leftDis+rightDis)/(rightDis-leftDis))*
				Width*Math.sin((rightDis-leftDis)/(2.0*Width));
		double deltaX, deltaY, deltaR;
		deltaX = Math.cos((r+(rightDis-leftDis)/(2.0*Width)))*temp;
		deltaY = Math.sin((r+(rightDis-leftDis)/(2.0*Width)))*temp;
		deltaR = (rightDis-leftDis)/(Width);
		x += deltaX;
		y += deltaY;
		r += deltaR;
	}
	/**reset after read*/
	public double FlushRead(Encoder enc){
		double Length_Meters = ToMeter(enc.get());
		enc.reset();
		return Length_Meters;
	}
	/**scale to meters*/
	public double ToMeter(int counts){
		//TODO convert the encoder units to meters
		//require diameter and units per round
		return -1;
	}
	
	public void clearAll(){
		LeftEncoder.reset();
		RightEncoder.reset();
		x=0;
		y=0;
		r=0;
	}
	
}
