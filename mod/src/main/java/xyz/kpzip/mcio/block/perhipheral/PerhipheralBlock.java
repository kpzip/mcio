package xyz.kpzip.mcio.block.perhipheral;

import java.util.function.Function;

import com.mojang.serialization.MapCodec;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

public abstract class PerhipheralBlock<T extends Enum<T> & StringRepresentable> extends BaseEntityBlock {
	
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	
	private Function<Properties, ? extends PerhipheralBlock<T>> super_constructor;

	public PerhipheralBlock(Properties properties, Function<Properties, ? extends PerhipheralBlock<T>> super_constructor) {
		super(properties);
		this.super_constructor = super_constructor;
		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		builder.add(POWERED);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
		return this.defaultBlockState();
	}
	
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(super_constructor);
	}

}
