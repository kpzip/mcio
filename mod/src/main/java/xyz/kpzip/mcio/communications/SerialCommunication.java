/**
 * 
 */
package xyz.kpzip.mcio.communications;

import java.io.IOException;

import org.jetbrains.annotations.Nullable;

import com.fazecast.jSerialComm.SerialPort;

import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.communications.packet.Packet;

/**
 * 
 * @author kpzip
 * 
 */
public class SerialCommunication implements CommunicationMode {
	
//	@Nullable
//	public static SerialPort currentPort = null;
	
	public static SerialPort[] availablePorts = new SerialPort[] {};
	
	
	private SerialPort port;
	
	public SerialCommunication(SerialPort p, int baud) throws IOException {
		port = p;
		if (port.openPort()) {
			port.setBaudRate(baud);
        	port.setNumDataBits(8);
        	port.setNumStopBits(SerialPort.ONE_STOP_BIT);
        	port.setParity(SerialPort.NO_PARITY);
        } else {
        	throw new IOException("Failed to connect to serial port: " + port.getSystemPortName() + " - " + port.getDescriptivePortName());
        }
	}
	
	public static void refresh() {
		SerialPort[] ports = SerialPort.getCommPorts();
		availablePorts = ports;
	}

	public static void init() {
		refresh();
		
//		SerialPort[] ports = SerialPort.getCommPorts();
//		MCIO.LOGGER.info("Available COM Ports:");
//        for (int i = 0; i < ports.length; i++) {
//        	MCIO.LOGGER.info(i + ": " + ports[i].getSystemPortName() + " - " + ports[i].getDescriptivePortName());
//        }
//        
//        int port_num = 0;
//        
//        SerialPort port = ports[port_num];
//        
//        if (port.openPort()) {
//        	port.setBaudRate(9600);
//        	port.setNumDataBits(8);
//        	port.setNumStopBits(SerialPort.ONE_STOP_BIT);
//        	port.setParity(SerialPort.NO_PARITY);
//        	currentPort = port;
//        	MCIO.LOGGER.info("Connected to serial port: " + ports[port_num].getSystemPortName());
//        }
//        else {
//        	MCIO.LOGGER.info("Failed to connect to serial port: " + ports[port_num].getSystemPortName());
//        }
		
	}
	
	private void sendData(byte[] message) {
		int bytesWritten = port.writeBytes(message, message.length);
		MCIO.LOGGER.info("Wrote " + bytesWritten + " bytes to the port.");
        
	}
	
	public static void close() {
//		if (SerialConnections.currentPort != null) {
//			MCIO.LOGGER.info("Closing Serial Connection...");
//			SerialConnections.currentPort.closePort();
//			SerialConnections.currentPort = null;
//		}
		for (SerialPort p : availablePorts) {
			if (p.isOpen()) {
				p.closePort();
			}
		}
	}

	@Override
	public void sendPacket(Packet p) {
		this.sendData(p.toBytes());
	}

	@Override
	public @Nullable Packet tryReceivePacket() {
		// TODO Auto-generated method stub
		return null;
	}

}
