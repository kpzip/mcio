package xyz.kpzip.mcio;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;
import xyz.kpzip.mcio.blockentity.peripheral.I2CBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralBiDirectionalComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralInputComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralOutputComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.PeripheralComponentRenderer;
import xyz.kpzip.mcio.blockentity.renderer.PeripheralControllerRenderer;
import xyz.kpzip.mcio.blockentity.renderer.model.MCIOModelLayers;
import xyz.kpzip.mcio.screen.GPIOScreen;
import xyz.kpzip.mcio.screen.MCIOScreens;
import xyz.kpzip.mcio.screen.MCUInterfaceScreen;

@Environment(EnvType.CLIENT)
public class MCIOClient implements ClientModInitializer {
	
	@Override
	public void onInitializeClient() {
		MCIO.LOGGER.info("MCIO Client Initialized.");
		
		// Screens
		MenuScreens.register(MCIOScreens.GPIO_SCREEN_HANDLER, GPIOScreen::new);
		MenuScreens.register(MCIOScreens.MCU_INTERFACE_SCREEN_HANDLER, MCUInterfaceScreen::new);
		
		// Block Entity Renderers
		BlockEntityRenderers.register(MCIOBlockEntities.PERHIPHERAL_INPUT_BLOCK_ENTITY, PeripheralComponentRenderer<PeripheralInputComponentBlockEntity>::new);
		BlockEntityRenderers.register(MCIOBlockEntities.PERHIPHERAL_OUTPUT_BLOCK_ENTITY, PeripheralComponentRenderer<PeripheralOutputComponentBlockEntity>::new);
		BlockEntityRenderers.register(MCIOBlockEntities.PERHIPHERAL_BIDIRECTIONAL_BLOCK_ENTITY, PeripheralComponentRenderer<PeripheralBiDirectionalComponentBlockEntity>::new);
		
		BlockEntityRenderers.register(MCIOBlockEntities.I2C_PERHIPHERAL_BLOCK_ENTITY, PeripheralControllerRenderer<I2CBlockEntity>::new);
		
		MCIOModelLayers.init();
	}
	
}