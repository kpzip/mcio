/**
 * 
 */
package xyz.kpzip.block;

import java.util.function.Function;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.kpzip.MCIO;

/**
 * 
 * @author kpzip
 * 
 */
public class MCIOBlocks {
	
	
	public static final Block GPIO_BLOCK = register("gpio", GPIO::new, BlockBehaviour.Properties.of().strength(4.0f));
	
	
	
	
	private static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
	    final ResourceLocation identifier = ResourceLocation.tryBuild(MCIO.MOD_ID, path);
	    final ResourceKey<Block> registryKey = ResourceKey.create(Registries.BLOCK, identifier);

	    final Block block = Blocks.register(registryKey, factory, settings);
	    Items.registerBlock(block);
	    return block;
	}
	
	public static void init() {
		// Static load the class
	}
	

	/**
	 * No.
	 */
	private MCIOBlocks() {}

}
