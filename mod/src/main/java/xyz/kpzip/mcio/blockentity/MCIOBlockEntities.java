package xyz.kpzip.mcio.blockentity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.blockentity.perhipheral.I2CBlockEntity;

public class MCIOBlockEntities {
	
	public static final BlockEntityType<GPIOBlockEntity> GPIO_BLOCK_ENTITY = register("gpio", FabricBlockEntityTypeBuilder.create(GPIOBlockEntity::new, MCIOBlocks.GPIO_BLOCK).build());
	public static final BlockEntityType<MCUInterfaceBlockEntity> MCU_INTERFACE_BLOCK_ENTITY = register("mcu_interface", FabricBlockEntityTypeBuilder.create(MCUInterfaceBlockEntity::new, MCIOBlocks.MCU_INTERFACE_BLOCK).build());
	
	// Prehipherals
	public static final BlockEntityType<I2CBlockEntity> I2C_PERHIPHERAL_BLOCK_ENTITY = register("i2c_perhipheral", FabricBlockEntityTypeBuilder.create(I2CBlockEntity::new, MCIOBlocks.I2C_PERHIPHERAL_BLOCK).build());
	
	
	public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
		final ResourceLocation id = ResourceLocation.tryBuild(MCIO.MOD_ID, path);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, blockEntityType);
	}
	
	public static void init() {}
	
}
