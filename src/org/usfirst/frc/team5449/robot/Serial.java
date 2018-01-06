package org.usfirst.frc.team5449.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Serial {
	public static SerialPort Arduino = new SerialPort(115200,Port.kMXP);
	
	//transform Double -> Bytes
	public static byte[] double2Bytes(double d) {  
        long value = Double.doubleToRawLongBits(d);  
        byte[] byteRet = new byte[8];  
        for (int i = 0; i < 8; i++) {  
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);  
        }  
        return byteRet;  
    }
	public static short byte2Short(byte[] arr) {  
	    int value = 0;  
	    for (int i = 0; i < 2; i++) {  
	        value |= ((int) (arr[i] & 0xff)) << (8 * i);  
	    }  
	    return (short)(value);  
	} 
	public static double bytes2Float(byte[] arr) {  
	    int value = 0;  
	    for (int i = 0; i < 4; i++) {  
	        value |= ((int) (arr[i] & 0xff)) << (8 * i);  
	    }  
	    return Float.intBitsToFloat(value);  
	} 
	
}
