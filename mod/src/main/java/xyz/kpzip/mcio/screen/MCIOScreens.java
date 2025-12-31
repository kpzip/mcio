/**
 * 
 */
package xyz.kpzip.mcio.screen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.screen.handler.GPIOScreenHandler;
import xyz.kpzip.mcio.screen.handler.MCUInterfaceScreenHandler;

/**
 * 
 * @author kpzip
 * 
 */
public class MCIOScreens {
	
	
	public static final MenuType<GPIOScreenHandler> GPIO_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.tryBuild(MCIO.MOD_ID, "gpio"), new MenuType<GPIOScreenHandler>(GPIOScreenHandler::new, FeatureFlags.VANILLA_SET));
	public static final MenuType<MCUInterfaceScreenHandler> MCU_INTERFACE_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU, Identifier.tryBuild(MCIO.MOD_ID, "mcu_interface"), new MenuType<MCUInterfaceScreenHandler>(MCUInterfaceScreenHandler::new, FeatureFlags.VANILLA_SET));
	
	public static void init() {}

	private MCIOScreens() {}

}
