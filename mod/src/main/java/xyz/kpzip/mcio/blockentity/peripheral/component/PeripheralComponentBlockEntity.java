package xyz.kpzip.mcio.blockentity.peripheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PeripheralComponentBlockEntity extends BlockEntity {

	public PeripheralComponentBlockEntity(BlockEntityType<? extends PeripheralComponentBlockEntity> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
	
	public abstract int getOutlineColor();

}
