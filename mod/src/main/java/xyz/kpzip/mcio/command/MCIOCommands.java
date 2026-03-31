package xyz.kpzip.mcio.command;

import java.util.Arrays;

import javax.sound.midi.MidiDevice;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permissions;
import xyz.kpzip.mcio.communications.MIDICommunication;

public final class MCIOCommands {
	
	public static void init(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext registryAccess, CommandSelection env) {
		dispatcher.register(
				Commands.literal("midi")
				.requires(source -> source.permissions().hasPermission(Permissions.COMMANDS_ADMIN))
				.then(Commands.literal("list").executes(ctx -> {
					
					MidiDevice.Info[] midiDeviceInfo = MIDICommunication.listOutputDevices();
					
					for (int i = 0; i < midiDeviceInfo.length; i++) {
		            	MidiDevice.Info info = midiDeviceInfo[i];
		            	ctx.getSource().sendSystemMessage(Component.translatable("commands.mcio.midi.list", String.valueOf(i), info.getName()));
		                //System.out.println("Available device #" + i + ": " + info.getName());
		            }
					return 1;
				}))
				.then(Commands.literal("set")
						.then(Commands.argument("devicenum", IntegerArgumentType.integer()).executes(ctx -> {
							int number = IntegerArgumentType.getInteger(ctx, "devicenum");
							
							MidiDevice.Info[] midiDeviceInfo = MIDICommunication.listOutputDevices();
							if (number < 0 || number >= midiDeviceInfo.length) {
								ctx.getSource().sendFailure(Component.translatable("commands.mcio.midi.set.failure.oob", midiDeviceInfo.length - 1, number));
								return -1;
							}
							
							MIDICommunication.setCurrentOutputDevice(midiDeviceInfo[number]);
							ctx.getSource().sendSuccess(() -> Component.translatable("commands.mcio.midi.set.success", midiDeviceInfo[number].getName()), true);
							return number;
				})))
				.then(Commands.literal("get").executes(ctx -> {
					MidiDevice.Info dev = MIDICommunication.getCurrentOutputDevice();
					if (dev == null) {
						ctx.getSource().sendFailure(Component.translatable("commands.mcio.midi.get.failure"));
						return -1;
					}
					int idx = Arrays.asList(MIDICommunication.listOutputDevices()).indexOf(dev);
					ctx.getSource().sendSuccess(() -> Component.translatable("commands.mcio.midi.get.success", dev.getName(), idx), false);
					return idx;
				}))
				.then(Commands.literal("info").executes(ctx -> {
					MidiDevice.Info dev = MIDICommunication.getCurrentOutputDevice();
					if (dev == null) {
						ctx.getSource().sendFailure(Component.translatable("commands.mcio.midi.info.failure"));
						return 0;
					}
					ctx.getSource().sendSystemMessage(Component.translatable("commands.mcio.midi.info.success.l1", dev.getName()));
					ctx.getSource().sendSystemMessage(Component.translatable("commands.mcio.midi.info.success.l2", dev.getDescription()));
					ctx.getSource().sendSystemMessage(Component.translatable("commands.mcio.midi.info.success.l3", dev.getVendor()));
					ctx.getSource().sendSystemMessage(Component.translatable("commands.mcio.midi.info.success.l4", dev.getVersion()));
					return 1;
				}))
				.then(Commands.literal("disable").executes(ctx -> {
					if (MIDICommunication.isDisabled()) {
						ctx.getSource().sendFailure(Component.translatable("commands.mcio.midi.disable.failure"));
						return 0;
					}
					MIDICommunication.disable();
					ctx.getSource().sendSuccess(() -> Component.translatable("commands.mcio.midi.disable.success"), true);
					return 1;
				}))
				);
		
		
		
	}
	
	
	
	private MCIOCommands() {
		// No.
	}

}
