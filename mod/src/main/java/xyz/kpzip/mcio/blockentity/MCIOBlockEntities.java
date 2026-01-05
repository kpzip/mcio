package xyz.kpzip.mcio.blockentity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.blockentity.peripheral.I2CBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralBiDirectionalComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralFillerComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralInputComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralOutputComponentBlockEntity;

public class MCIOBlockEntities {
	
	public static final BlockEntityType<GPIOBlockEntity> GPIO_BLOCK_ENTITY = register("gpio", FabricBlockEntityTypeBuilder.create(GPIOBlockEntity::new, MCIOBlocks.GPIO_BLOCK).build());
	public static final BlockEntityType<MCUInterfaceBlockEntity> MCU_INTERFACE_BLOCK_ENTITY = register("mcu_interface", FabricBlockEntityTypeBuilder.create(MCUInterfaceBlockEntity::new, MCIOBlocks.MCU_INTERFACE_BLOCK).build());
	
	// Peripheral Components
	public static final BlockEntityType<PeripheralInputComponentBlockEntity> PERIPHERAL_INPUT_BLOCK_ENTITY = register("peripheral_input", FabricBlockEntityTypeBuilder.create(PeripheralInputComponentBlockEntity::new, MCIOBlocks.PERIPHERAL_INPUT).build());
	public static final BlockEntityType<PeripheralOutputComponentBlockEntity> PERIPHERAL_OUTPUT_BLOCK_ENTITY = register("peripheral_output", FabricBlockEntityTypeBuilder.create(PeripheralOutputComponentBlockEntity::new, MCIOBlocks.PERIPHERAL_OUTPUT).build());
	public static final BlockEntityType<PeripheralBiDirectionalComponentBlockEntity> PERIPHERAL_BIDIRECTIONAL_BLOCK_ENTITY = register("peripheral_bidirect", FabricBlockEntityTypeBuilder.create(PeripheralBiDirectionalComponentBlockEntity::new, MCIOBlocks.PERIPHERAL_BIDIRECTIONAL).build());
	public static final BlockEntityType<PeripheralFillerComponentBlockEntity> PERIPHERAL_FILLER_BLOCK_ENTITY = register("peripheral_filler", FabricBlockEntityTypeBuilder.create(PeripheralFillerComponentBlockEntity::new, MCIOBlocks.PERIPHERAL_FILLER).build());
	
	// Peripherals
	public static final BlockEntityType<I2CBlockEntity> I2C_PERIPHERAL_BLOCK_ENTITY = register("i2c_peripheral", FabricBlockEntityTypeBuilder.create(I2CBlockEntity::new, MCIOBlocks.I2C_PERIPHERAL_BLOCK).build());
	
	
	public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
		final Identifier id = Identifier.tryBuild(MCIO.MOD_ID, path);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, blockEntityType);
	}
	
	public static void init() {}
	
}
