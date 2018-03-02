package sensors;

import org.usfirst.frc.team5449.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	
	
	
	private static double offset = 0;
	
	public static void reset(){
		offset = 0;
	}
	
	public static void set_offset(double offset2){
		offset = offset2;
	}
	
	public static double getAngle(){
		Timer out_of_time = new Timer();
		byte[] request = {10};
		out_of_time.reset();
		out_of_time.start();
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
		while (Serial.Arduino.getBytesReceived()<2){
			if (out_of_time.get()>=0.04){
				return 1440;
			}
			if (Serial.Arduino.getBytesReceived()>=2){
				SmartDashboard.putString("OK?", "OK");
				break;
			}
		}
		byte[] buf = new byte[2];
		buf = Serial.Arduino.read(2);
		byte[] data = new byte[2];
		data[0] = buf[1];
		data[1] = buf[0];
		double val =  180.0*(Serial.byte2Short(data))/32768.0 + offset;
		if (val > 180){
			val -= 360;
		}
		if (val <= -180){
			val += 360;
		}
		return val;
	}
	
	public static double[] getData(){
		double[] val = {0,0};
		Timer out_of_time = new Timer();
		byte[] request = {9};
		out_of_time.reset();
		out_of_time.start();
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
		while (Serial.Arduino.getBytesReceived()<4){
			if (out_of_time.get()>=0.04){
				return val;
			}
			if (Serial.Arduino.getBytesReceived()>=4){
				break;
			}
		}
		byte[] buf = new byte[4];
		buf = Serial.Arduino.read(4);
		
		byte[] dataA = new byte[2];
		dataA[0] = buf[1];
		dataA[1] = buf[0];
		double val2 =  180.0*(Serial.byte2Short(dataA))/32768.0 + offset;
		
		byte[] dataB = new byte[2];
		dataB[0] = buf[2];
		dataB[1] = buf[3];
		double val3 =  2000.0*(Serial.byte2Short(dataB))/32768.0;
		
		if (val2 > 180){
			val2 -= 360;
		}
		if (val2 <= -180){
			val2 += 360;
		}
		val[0] = val2;
		val[1] = val3;
		
		
		return val;//TODO
	}
	

	
}
