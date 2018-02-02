package sensors;

import edu.wpi.first.wpilibj.Timer;

public class EncoderModule {
	
	private double[] offsets = {0,0};
	private double[] field_offsets = {2300,340};
	public EncoderModule(){
		
	}
	
	public double getX(){
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
		return (double)Serial.bytes2Float(buf) + this.offsets[0] + field_offsets[0];	
	}
	public double getY(){
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
		return (double)Serial.bytes2Float(buf) + this.offsets[1] + field_offsets[1];	
	}
	
	public void setOffset(double[] Offsets){
		this.offsets[0] += Offsets[0];
		this.offsets[1] += Offsets[1];
	}
	
	public void setfieldOffset(double[] Offsets){
		this.field_offsets = Offsets;
	}
	
	
	public void reset(){
		byte[] request = {7};
		this.offsets[0] = 0;
		this.offsets[1] = 0;
		Serial.Arduino.read(Serial.Arduino.getBytesReceived());
		Serial.Arduino.write(request, 1);
	}

}
