package xyz.kpzip.mcio.blockentity.perhipheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PerhipheralComponentBlockEntity extends BlockEntity {

	public PerhipheralComponentBlockEntity(BlockEntityType<? extends PerhipheralComponentBlockEntity> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
	
	public abstract int getOutlineColor();

}
