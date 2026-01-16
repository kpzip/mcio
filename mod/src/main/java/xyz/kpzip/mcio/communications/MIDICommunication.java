package xyz.kpzip.mcio.communications;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;

public class MIDICommunication {
	
	private static MidiDevice currentOutputDevice = null;
	
	public static int fromNoteBlockPitch(int p, NoteBlockInstrument i) {
		return p + 42;
	}
	
	public static void init() {

        try {
            MidiDevice.Info[] midiDeviceInfo = MidiSystem.getMidiDeviceInfo();

            for (MidiDevice.Info info : midiDeviceInfo) {
                System.out.println("Available device: " + info.getName());
            }
            
            if (midiDeviceInfo.length == 0) {
            	return;
            }
            
            currentOutputDevice = MidiSystem.getMidiDevice(midiDeviceInfo[0]);

            if (currentOutputDevice == null) {
                System.out.println("MIDI device \"" + currentOutputDevice.getDeviceInfo().getName() + "\" not found. Using default receiver.");
            }

            if (!currentOutputDevice.isOpen()) {
            	currentOutputDevice.open();
                System.out.println("Opened device: " + currentOutputDevice.getDeviceInfo().getName());
            }

        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
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
