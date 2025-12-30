package xyz.kpzip.mcio.blockentity.perhipheral.component;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class PerhipheralInputComponentBlockEntity extends PerhipheralComponentBlockEntity {

	public PerhipheralInputComponentBlockEntity(BlockPos blockPos,
			BlockState blockState) {
		super(MCIOBlockEntities.PERHIPHERAL_INPUT_BLOCK_ENTITY, blockPos, blockState);
	}

}
