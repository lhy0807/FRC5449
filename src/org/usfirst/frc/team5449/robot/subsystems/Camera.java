package org.usfirst.frc.team5449.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class Camera extends Subsystem {
	
	//public UsbCamera CameraFront = new UsbCamera("FrontCam", 0);
    //private MjpegServer Server = new MjpegServer("CameraServer", 5449);//config the port number in DS?
    //private MjpegServer VisionServer = new MjpegServer("VisionProcessingServer", 1111);//config the port number in DS?
	UsbCamera c1 = new UsbCamera("cam0",0);
    CameraServer server = CameraServer.getInstance();
    
    public Camera(){

    	c1.setResolution(1024, 768);
    	c1.setFPS(24);
    	
    	server.startAutomaticCapture(c1);
	   //set camera
	  // CameraFront.setResolution(800, 600);
	  // CameraFront.setFPS(24); 
	   //set feed
	   //Server.setSource(CameraFront);
	   //VisionServer.setSource(CameraFront);
	}
    
    public void StopCamera(){
    	//Server.setSource(null);
    }
	
	public void initDefaultCommand() {
        //TODO setDefaultCommand(new switchCamera());???
    }
}

