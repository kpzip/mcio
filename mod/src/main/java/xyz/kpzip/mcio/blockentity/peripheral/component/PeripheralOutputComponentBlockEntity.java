package xyz.kpzip.mcio.blockentity.peripheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class PeripheralOutputComponentBlockEntity extends PeripheralComponentBlockEntity {

	public PeripheralOutputComponentBlockEntity(BlockPos blockPos,
			BlockState blockState) {
		super(MCIOBlockEntities.PERIPHERAL_OUTPUT_BLOCK_ENTITY, blockPos, blockState);
	}
	
	@Override
	public int getOutlineColor() {
		return DyeColor.BLUE.getTextColor();
	}

}
