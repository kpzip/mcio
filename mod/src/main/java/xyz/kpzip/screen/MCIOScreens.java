/**
 * 
 */
package xyz.kpzip.screen;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import xyz.kpzip.MCIO;
import xyz.kpzip.screen.handler.GPIOScreenHandler;

/**
 * 
 * @author kpzip
 * 
 */
public class MCIOScreens {
	
	
	public static final MenuType<GPIOScreenHandler> GPIO_SCREEN_HANDLER = Registry.register(BuiltInRegistries.MENU, ResourceLocation.tryBuild(MCIO.MOD_ID, "gpio"), new MenuType<GPIOScreenHandler>(GPIOScreenHandler::new, FeatureFlags.VANILLA_SET));
	
	public static void init() {}

	private MCIOScreens() {}

}
