/**
 * 
 */
package xyz.kpzip.mcio.communications.packet;

/**
 * 
 * @author kpzip
 * 
 */
public class GPIOSetPacket extends Packet {

	
	private int pin;
	private boolean on;
	
	/**
	 * 
	 */
	public GPIOSetPacket(int pin, boolean on) {
		this.pin = pin;
		this.on = on;
	}

	@Override
	public byte packetId() {
		return 1;
	}

	@Override
	public byte dataByte1() {
		return (byte) pin;
	}

	@Override
	public byte dataByte2() {
		return (byte) (on ? 1 : 0);
	}

	@Override
	public byte dataByte3() {
		return 0;
	}

}
