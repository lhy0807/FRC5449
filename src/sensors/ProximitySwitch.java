package sensors;

import edu.wpi.first.wpilibj.DigitalInput;

public class ProximitySwitch {
	
	//Assumes that the sensor is digital
	//requires a digital input port
	private DigitalInput d1;
	
	public ProximitySwitch(int port){
		d1 = new DigitalInput(port);
	}
	
	//return
	public boolean get(){
		return d1.get();
	}
	
}
