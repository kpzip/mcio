/**
 * 
 */
package xyz.kpzip.mcio.communications;

import org.jetbrains.annotations.Nullable;

import xyz.kpzip.mcio.communications.packet.Packet;

/**
 * 
 * @author kpzip
 * 
 */
public interface CommunicationMode {
	
	public void sendPacket(Packet p);
	
	@Nullable
	public Packet tryReceivePacket();

}
