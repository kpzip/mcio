package xyz.kpzip.mcio.item.component;

import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import xyz.kpzip.mcio.MCIO;

public final class MCIOComponents {

	public static final DataComponentType<WrenchState> WRENCH_DATA = Registry.register(
		    BuiltInRegistries.DATA_COMPONENT_TYPE,
		    Identifier.tryBuild(MCIO.MOD_ID, "my_component"),
		    DataComponentType.<WrenchState>builder().persistent(WrenchState.CODEC)
		    .networkSynchronized(WrenchState.STREAM_CODEC).cacheEncoding().build()
		);
	
	
	public static void init() {
		// Static load the class
	}
	

	/**
	 * No.
	 */
	private MCIOComponents() {}
}
