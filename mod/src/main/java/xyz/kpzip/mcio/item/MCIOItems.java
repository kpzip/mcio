package xyz.kpzip.mcio.item;

import java.util.function.Function;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchInfoComponent;

public class MCIOItems {
	
	public static final Item WRENCH = register("wrench", Item::new, new Item.Properties().stacksTo(1).component(MCIOComponents.WRENCH_DATA, new WrenchInfoComponent()));
	
	
	private static Item register(String path, Function<Item.Properties, Item> factory, Item.Properties settings) {
	    final Identifier identifier = Identifier.tryBuild(MCIO.MOD_ID, path);
	    final ResourceKey<Item> registryKey = ResourceKey.create(Registries.ITEM, identifier);

	    final Item block = Items.registerItem(registryKey, factory, settings);
	    return block;
	}
	
	public static void init() {
		// Static load the class
	}
	

	/**
	 * No.
	 */
	private MCIOItems() {}

}
