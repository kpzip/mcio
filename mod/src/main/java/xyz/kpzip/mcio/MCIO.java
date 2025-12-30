package xyz.kpzip.mcio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;
import xyz.kpzip.mcio.communications.SerialCommunication;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.screen.MCIOScreens;

public class MCIO implements ModInitializer, ServerLifecycleEvents.ServerStopped {
	
	
	public static final String MOD_ID = "mcio";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("MCIO Loaded.");
		
		// Static load a bunch of classes
		MCIOBlocks.init();
		MCIOItems.init();
		MCIOBlockEntities.init();
		MCIOScreens.init();
		MCIOComponents.init();
		
		ServerLifecycleEvents.SERVER_STOPPED.register(this);
		
		// TODO should only happen if the block is opened
		SerialCommunication.init();
		
	}

	@Override
	public void onServerStopped(MinecraftServer server) {
		
		SerialCommunication.close();
		
	}
}