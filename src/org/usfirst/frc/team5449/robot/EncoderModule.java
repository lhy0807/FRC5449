package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderModule {
	
	public static double getX(){
		Timer out_of_time = new Timer();
		byte[] request = {9};
		out_of_time.reset();
		out_of_time.start();
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
		while (Serial.Arduino.getBytesReceived()<4){
			if (out_of_time.get()>=0.1){
				return 2560;
			}
			if (Serial.Arduino.getBytesReceived()>=4){
				break;
			}
		}
		byte[] buf = new byte[4];
		buf = Serial.Arduino.read(4);
		return (double)Serial.bytes2Float(buf);	
	}
	public static double getY(){
		Timer out_of_time = new Timer();
		byte[] request = {8};
		out_of_time.reset();
		out_of_time.start();
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
		while (Serial.Arduino.getBytesReceived()<4){
			if (out_of_time.get()>=0.1){
				return 2560;
			}
			if (Serial.Arduino.getBytesReceived()>=4){
				break;
			}
		}
		byte[] buf = new byte[4];
		buf = Serial.Arduino.read(4);
		return (double)Serial.bytes2Float(buf);	
	}
	public static void reset(){
		byte[] request = {7};
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
	}

}
