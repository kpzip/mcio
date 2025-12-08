/**
 * 
 */
package xyz.kpzip.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.redstone.Orientation;
import xyz.kpzip.MCIO;
import xyz.kpzip.blockentity.GPIOBlockEntity;
import xyz.kpzip.serial.SerialConnections;

/**
 * 
 * @author kpzip
 * 
 */
public class GPIO extends Block implements EntityBlock {
	
	static final BooleanProperty POWERED = BooleanProperty.create("powered");

	public GPIO(BlockBehaviour.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		boolean isPowered = blockPlaceContext.getLevel().hasNeighborSignal(blockPlaceContext.getClickedPos());
		if (!blockPlaceContext.getLevel().isClientSide()) {
			updateGPIOState(isPowered);
		}
		return this.defaultBlockState().setValue(POWERED, isPowered);
	}
	
	@Override
	protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, Orientation orientation, boolean bl) {
		super.neighborChanged(blockState, level, blockPos, block, orientation, bl);
		
		if (!level.isClientSide()) {
			
			boolean state = blockState.getValue(POWERED);
			boolean isPowered = level.hasNeighborSignal(blockPos);
			if (isPowered != state) {
				level.setBlock(blockPos, blockState.setValue(POWERED, isPowered), 2);
				updateGPIOState(isPowered);
			}
			
		}
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new GPIOBlockEntity(blockPos, blockState);
	}

	
	private void updateGPIOState(boolean state) {
		MCIO.LOGGER.info("GPIO Toggled " + (state ? "on" : "off"));
		SerialConnections.sendData(state ? new byte[] {1} : new byte[] {0});
	}
	
	
	

}
