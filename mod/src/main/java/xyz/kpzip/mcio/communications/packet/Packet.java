/**
 * 
 */
package xyz.kpzip.mcio.communications.packet;

/**
 * 
 * @author kpzip
 * 
 */
public abstract class Packet {

	/**
	 * 
	 */
	public Packet() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract byte packetId();
	
	public abstract byte dataByte1();
	
	public abstract byte dataByte2();
	
	public abstract byte dataByte3();

	
	public byte[] toBytes() {
		return new byte[] {this.packetId(), };
	}

}
