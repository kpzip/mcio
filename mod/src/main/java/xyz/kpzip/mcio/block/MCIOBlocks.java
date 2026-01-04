/**
 * 
 */
package xyz.kpzip.mcio.block;

import java.util.function.Function;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.block.peripheral.I2C;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralBiDirectional;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralInput;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralOutput;

/**
 * 
 * @author kpzip
 * 
 */
public class MCIOBlocks {
	
	
	public static final Block GPIO_BLOCK = register("gpio", GPIO::new, BlockBehaviour.Properties.of().strength(4.0f));
	public static final Block MCU_INTERFACE_BLOCK = register("mcu_interface", MCUInterface::new, BlockBehaviour.Properties.of().strength(4.0f));
	
	// Peripheral building blocks
	public static final Block PERHIPHERAL_INPUT = register("perhipheral_input", PeripheralInput::new, BlockBehaviour.Properties.of().strength(4.0f));
	public static final Block PERHIPHERAL_OUTPUT = register("perhipheral_output", PeripheralOutput::new, BlockBehaviour.Properties.of().strength(4.0f));
	public static final Block PERHIPHERAL_BIDIRECTIONAL = register("perhipheral_bidirect", PeripheralBiDirectional::new, BlockBehaviour.Properties.of().strength(4.0f));
	
	
	// Peripherals
	public static final Block I2C_PERHIPHERAL_BLOCK = register("i2c", I2C::new, BlockBehaviour.Properties.of().strength(4.0f));
	
	
	private static Block register(String path, Function<BlockBehaviour.Properties, Block> factory, BlockBehaviour.Properties settings) {
	    final Identifier identifier = Identifier.tryBuild(MCIO.MOD_ID, path);
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
