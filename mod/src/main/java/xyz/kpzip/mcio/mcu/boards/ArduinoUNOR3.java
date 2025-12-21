/**
 * 
 */
package xyz.kpzip.mcio.mcu.boards;

import xyz.kpzip.mcio.communications.CommunicationMode;
import xyz.kpzip.mcio.communications.packet.GPIOSetPacket;

/**
 * 
 * @author kpzip
 * 
 */
public class ArduinoUNOR3 extends MCUBoard {
	
	private CommunicationMode comms;

	/**
	 * 
	 */
	public ArduinoUNOR3(CommunicationMode mode) {
		this.comms = mode;
	}

	@Override
	public void setPin(int pin, boolean state) {
		comms.sendPacket(new GPIOSetPacket(pin, state));
		
	}

}
