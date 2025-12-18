/**
 * 
 */
package xyz.kpzip.mcio.serial;

import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.Nullable;

import com.fazecast.jSerialComm.SerialPort;

import xyz.kpzip.mcio.MCIO;

/**
 * 
 * @author kpzip
 * 
 */
public class SerialConnections {
	
	@Nullable
	public static SerialPort currentPort = null;

	public static void init() {
		
		SerialPort[] ports = SerialPort.getCommPorts();
		MCIO.LOGGER.info("Available COM Ports:");
        for (int i = 0; i < ports.length; i++) {
        	MCIO.LOGGER.info(i + ": " + ports[i].getSystemPortName() + " - " + ports[i].getDescriptivePortName());
        }
        
        int port_num = 0;
        
        SerialPort port = ports[port_num];
        
        if (port.openPort()) {
        	port.setBaudRate(9600);
        	port.setNumDataBits(8);
        	port.setNumStopBits(SerialPort.ONE_STOP_BIT);
        	port.setParity(SerialPort.NO_PARITY);
        	currentPort = port;
        	MCIO.LOGGER.info("Connected to serial port: " + ports[port_num].getSystemPortName());
        }
        else {
        	MCIO.LOGGER.info("Failed to connect to serial port: " + ports[port_num].getSystemPortName());
        }
		
	}
	
	public static void sendData(String message) {
		sendData(message.getBytes(StandardCharsets.UTF_8));
	}
	
	public static void sendData(byte[] message) {
		int bytesWritten = currentPort.writeBytes(message, message.length);
        MCIO.LOGGER.info("Wrote " + bytesWritten + " bytes to the port.");
	}
	
	
	private SerialConnections() {}

}
