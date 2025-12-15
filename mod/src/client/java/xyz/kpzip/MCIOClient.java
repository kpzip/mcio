package xyz.kpzip;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import xyz.kpzip.screen.GPIOScreen;
import xyz.kpzip.screen.MCIOScreens;

public class MCIOClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MenuScreens.register(MCIOScreens.GPIO_SCREEN_HANDLER, GPIOScreen::new);
	}
}