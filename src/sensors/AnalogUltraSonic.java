package sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltraSonic {
	private AnalogInput analoginput;
	AnalogUltraSonic(int port){
		this.analoginput = new AnalogInput(port);
	}
	
	public double get(){
		return (analoginput.getVoltage()/1024.0 * 3.000);//TODO
	}
}
