package org.usfirst.frc.team5449.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class Camera extends Subsystem {
	
	public UsbCamera CameraFront = new UsbCamera("FrontCam", 0);
    private MjpegServer Server = new MjpegServer("CameraServer", 5449);//config the port number in DS?
    private MjpegServer VisionServer = new MjpegServer("VisionProcessingServer", 1111);//config the port number in DS?
    
    public Camera(){
	   //set camera
	   CameraFront.setResolution(800, 600);
	   CameraFront.setFPS(24); 
	   //set feed
	   Server.setSource(CameraFront);
	   VisionServer.setSource(CameraFront);
	}
    
    public void StopCamera(){
    	Server.setSource(null);
    }
	
	public void initDefaultCommand() {
        //TODO setDefaultCommand(new switchCamera());???
    }
}

