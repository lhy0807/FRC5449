package sensors;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Gyro {
	
	
	
	private static double offset = 0;
	public static double getAngle(){
		Timer out_of_time = new Timer();
		byte[] request = {10};
		out_of_time.reset();
		out_of_time.start();
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
		while (Serial.Arduino.getBytesReceived()<6){
			if (out_of_time.get()>=0.02){
				return 1440;
			}
			if (Serial.Arduino.getBytesReceived()>=6){
				break;
			}
		}
		byte[] buf = new byte[6];
		buf = Serial.Arduino.read(6);
		byte[] data = new byte[2];
		data[0] = buf[1];
		data[1] = buf[0];
		double val =  180.0*(Serial.byte2Short(data))/32768.0;
		return val;
		
	}
	

	
}
