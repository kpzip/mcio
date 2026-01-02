package xyz.kpzip.mcio.blockentity.perhipheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class PerhipheralBiDirectionalComponentBlockEntity extends PerhipheralComponentBlockEntity {

	public PerhipheralBiDirectionalComponentBlockEntity(BlockPos blockPos,
			BlockState blockState) {
		super(MCIOBlockEntities.PERHIPHERAL_BIDIRECTIONAL_BLOCK_ENTITY, blockPos, blockState);
	}
	
	@Override
	public int getOutlineColor() {
		return DyeColor.YELLOW.getTextColor();
	}

}
