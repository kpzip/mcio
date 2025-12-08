package xyz.kpzip.blockentity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import xyz.kpzip.MCIO;
import xyz.kpzip.block.MCIOBlocks;

public class MCIOBlockEntities {
	
	public static final BlockEntityType<GPIOBlockEntity> GPIO_BLOCK_ENTITY = register("gpio", FabricBlockEntityTypeBuilder.create(GPIOBlockEntity::new, MCIOBlocks.GPIO_BLOCK).build());
	
	
	public static <T extends BlockEntityType<?>> T register(String path, T blockEntityType) {
		final ResourceLocation id = ResourceLocation.tryBuild(MCIO.MOD_ID, path);
		return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, blockEntityType);
	}
	
	public static void init() {}
	
}
