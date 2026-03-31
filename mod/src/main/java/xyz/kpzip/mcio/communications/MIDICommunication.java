package xyz.kpzip.mcio.communications;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import xyz.kpzip.mcio.MCIO;

public class MIDICommunication {
	
	private static MidiDevice currentOutputDevice = null;
	
	public static int fromNoteBlockPitch(int p, NoteBlockInstrument i) {
		return p + 42;
	}
	
	public static boolean isDisabled() {
		return currentOutputDevice == null;
	}
	
	public static void disable() {
		if (currentOutputDevice != null) {
			currentOutputDevice.close();
		}
		currentOutputDevice = null;
	}
	
	public static void setCurrentOutputDevice(MidiDevice.Info info) {
		if (info == null) {
			disable();
		}
		try {
			MidiDevice device = MidiSystem.getMidiDevice(info);
			if (currentOutputDevice != null) {
				currentOutputDevice.close();
			}
			if (!device.isOpen()) {
				device.open();
			}
			currentOutputDevice = device;
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	public static MidiDevice.Info getCurrentOutputDevice() {
		if (currentOutputDevice == null) {
			return null;
		}
		return currentOutputDevice.getDeviceInfo();
	}
	
	public static MidiDevice.Info[] listOutputDevices() {
		 return MidiSystem.getMidiDeviceInfo();
	}
	
	public static void init() {

//        try {
//            MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();
//
//            for (int i = 0; i < midiDeviceInfo.length; i++) {
//            	MidiDevice.Info info = midiDeviceInfo[i];
//                System.out.println("Available device #" + i + ": " + info.getName());
//            }
//            
//            Scanner input = new Scanner(System.in);
//            int choice = input.nextInt();
//            
//            if (midiDeviceInfo.length == 0) {
//            	return;
//            }
//            
//            currentOutputDevice = MidiSystem.getMidiDevice(midiDeviceInfo[choice]);
//
//            if (currentOutputDevice == null) {
//                System.out.println("MIDI device \"" + currentOutputDevice.getDeviceInfo().getName() + "\" not found. Using default receiver.");
//            }
//
//            if (!currentOutputDevice.isOpen()) {
//            	currentOutputDevice.open();
//                System.out.println("Opened device: " + currentOutputDevice.getDeviceInfo().getName());
//            }
//
//        } catch (MidiUnavailableException e) {
//            e.printStackTrace();
//        }
	}
	
	private static Receiver getReceiver() throws MidiUnavailableException {
		if (currentOutputDevice == null) {
			return MidiSystem.getReceiver();
		}
		return currentOutputDevice.getReceiver();
	}
	
	public static void noteOn(int pitch, int vel, int channel) {
		try {
			Receiver receiver = getReceiver();
			ShortMessage noteOn = new ShortMessage();
	        noteOn.setMessage(ShortMessage.NOTE_ON, channel, pitch, vel);
	        long timeStamp = -1;
	        receiver.send(noteOn, timeStamp);
	        // MCIO.LOGGER.info("Sent note on message " + pitch + " on device " + currentOutputDevice.getDeviceInfo().getName());
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void noteOff(int pitch, int vel, int channel) {
		try {
			Receiver receiver = getReceiver();
			ShortMessage noteOff = new ShortMessage();
	        noteOff.setMessage(ShortMessage.NOTE_OFF, channel, pitch, vel);
	        long timeStamp = -1;
	        receiver.send(noteOff, timeStamp);
	        // MCIO.LOGGER.info("Sent note off message " + pitch + " on device " + currentOutputDevice.getDeviceInfo().getName());
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void explodeCapacitorOn() {
		try {
			Receiver receiver = getReceiver();
			ShortMessage noteOff = new ShortMessage();
	        noteOff.setMessage(ShortMessage.CONTROL_CHANGE, 0, 69, 0x01);
	        long timeStamp = -1;
	        receiver.send(noteOff, timeStamp);
	        MCIO.LOGGER.info("Explode Capacitor On.");
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void explodeCapacitorOff() {
		try {
			Receiver receiver = getReceiver();
			ShortMessage noteOff = new ShortMessage();
	        noteOff.setMessage(ShortMessage.CONTROL_CHANGE, 0, 69, 0);
	        long timeStamp = -1;
	        receiver.send(noteOff, timeStamp);
	        MCIO.LOGGER.info("Explode Capacitor Off.");
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void deinit() {
		if (currentOutputDevice != null) {
			currentOutputDevice.close();
		}
	}
}
