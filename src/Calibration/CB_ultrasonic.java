package Calibration;

import sensors.AnalogUltraSonic;

public class CB_ultrasonic {

	private double[] Coordinates = { 0, 0 };// From EncoderModule
	private double heading = 0;// front = 0,CW = +
	private AnalogUltraSonic ultrasonic;

	protected CB_ultrasonic(double[] Coordinates, double Heading, AnalogUltraSonic ultrasonic) {
		this.Coordinates = Coordinates;
		this.heading = Heading;
		this.ultrasonic = ultrasonic;
	}
	
	
	
	protected double[] getPos() {
		return this.Coordinates;
	}

	protected double getHeading() {
		return this.heading;
	}

	protected double get() {
		return this.ultrasonic.get();
	}
}
