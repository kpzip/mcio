package xyz.kpzip.mcio.item.component;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import xyz.kpzip.mcio.MCIO;

public final class MCIOComponents {

	public static final DataComponentType<WrenchInfoComponent> WRENCH_DATA = Registry.register(
		    BuiltInRegistries.DATA_COMPONENT_TYPE,
		    ResourceLocation.fromNamespaceAndPath(MCIO.MOD_ID, "my_component"),
		    DataComponentType.<WrenchInfoComponent>builder().persistent(WrenchInfoComponent.CODEC)
		    .networkSynchronized(WrenchInfoComponent.STREAM_CODEC).cacheEncoding().build()
		);
	
	
	public static void init() {
		// Static load the class
	}
	

	/**
	 * No.
	 */
	private MCIOComponents() {}
}
