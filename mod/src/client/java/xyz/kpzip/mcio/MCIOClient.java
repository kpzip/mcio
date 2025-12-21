package xyz.kpzip.mcio;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import xyz.kpzip.mcio.screen.GPIOScreen;
import xyz.kpzip.mcio.screen.MCIOScreens;
import xyz.kpzip.mcio.screen.MCUInterfaceScreen;

@Environment(EnvType.CLIENT)
public class MCIOClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		MCIO.LOGGER.info("MCIO Client Initialized");
		MenuScreens.register(MCIOScreens.GPIO_SCREEN_HANDLER, GPIOScreen::new);
		MenuScreens.register(MCIOScreens.MCU_INTERFACE_SCREEN_HANDLER, MCUInterfaceScreen::new);
	}
	
}