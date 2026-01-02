package xyz.kpzip.mcio.block.perhipheral;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import xyz.kpzip.mcio.block.perhipheral.state.I2CBlockStates;
import xyz.kpzip.mcio.blockentity.perhipheral.I2CBlockEntity;

public class I2C extends PerhipheralBlock {
	
	public static final EnumProperty<I2CBlockStates> PART = EnumProperty.create("part", I2CBlockStates.class);

	public I2C(Properties properties) {
		super(properties, I2C::new);
		this.registerDefaultState(this.defaultBlockState().setValue(PART, I2CBlockStates.SINGLE));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new I2CBlockEntity(blockPos, blockState);
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(PART);
	}

	@Override
	public int requiredInputs(BlockPos pos, BlockState state, Level level) {
		return 0;
	}

	@Override
	public int requiredOutputs(BlockPos pos, BlockState state, Level level) {
		return 0;
	}

	@Override
	public int requiredBiDirects(BlockPos pos, BlockState state, Level level) {
		return 2;
	}

}
