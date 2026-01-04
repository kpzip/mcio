package xyz.kpzip.mcio.blockentity.peripheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class PeripheralInputComponentBlockEntity extends PeripheralComponentBlockEntity {

	public PeripheralInputComponentBlockEntity(BlockPos blockPos,
			BlockState blockState) {
		super(MCIOBlockEntities.PERHIPHERAL_INPUT_BLOCK_ENTITY, blockPos, blockState);
	}
	
	@Override
	public int getOutlineColor() {
		return DyeColor.RED.getTextColor();
	}

}
