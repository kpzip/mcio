package xyz.kpzip.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class GPIOBlockEntity extends BlockEntity {

	public GPIOBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MCIOBlockEntities.GPIO_BLOCK_ENTITY, blockPos, blockState);
	}

}
