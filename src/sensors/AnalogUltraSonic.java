package sensors;

import edu.wpi.first.wpilibj.AnalogInput;

public class AnalogUltraSonic {
	private AnalogInput analoginput;
	public AnalogUltraSonic(int port){
		this.analoginput = new AnalogInput(port);
	}
	
	public double get(){
		return (analoginput.getVoltage() * 0.20f * 1.024 * 3.0);
		
	}
}
