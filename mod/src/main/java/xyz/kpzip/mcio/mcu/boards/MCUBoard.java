/**
 * 
 */
package xyz.kpzip.mcio.mcu.boards;

/**
 * 
 * @author kpzip
 * 
 */
public abstract class MCUBoard {
	
	public static final UnconnectedBoard UNCONNECTED = new UnconnectedBoard();

	/**
	 * 
	 */
	public MCUBoard() {
		// TODO Auto-generated constructor stub
	}
	
	public abstract void setPin(int pin, boolean state);

}
