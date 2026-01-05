package xyz.kpzip.mcio.block.peripheral.mutiblock;

import org.jspecify.annotations.Nullable;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralFillerComponentBlockEntity;

public class PeripheralFiller extends PeripheralMultiblockComponent{
	
	public static final BooleanProperty IS_MULTIBLOCK = BooleanProperty.create("is_multiblock");

	public PeripheralFiller(Properties arg0) {
		super(arg0);
		this.registerDefaultState(this.defaultBlockState().setValue(IS_MULTIBLOCK, false));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(IS_MULTIBLOCK);
	}
	
	@Override
	public @Nullable BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		return this.defaultBlockState();
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new PeripheralFillerComponentBlockEntity(blockPos, blockState);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(PeripheralFiller::new);
	}
	
	@Override
	public boolean isSelectable(BlockState state) {
		return !state.getValue(IS_MULTIBLOCK);
	}

}
