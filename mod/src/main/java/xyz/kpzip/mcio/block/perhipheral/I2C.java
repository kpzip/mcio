package xyz.kpzip.mcio.block.perhipheral;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.block.perhipheral.state.I2CBlockStates;
import xyz.kpzip.mcio.blockentity.perhipheral.I2CBlockEntity;

public class I2C extends PerhipheralBlock<I2CBlockStates> {

	public I2C(Properties properties) {
		super(properties, I2CBlockStates.SINGLE, I2CBlockStates.class, I2C::new);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new I2CBlockEntity(blockPos, blockState);
	}

}
