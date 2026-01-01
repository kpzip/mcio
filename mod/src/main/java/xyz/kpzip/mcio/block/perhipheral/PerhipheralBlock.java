package xyz.kpzip.mcio.block.perhipheral;

import java.util.function.Function;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchInfoComponent;

public abstract class PerhipheralBlock<T extends Enum<T> & StringRepresentable> extends BaseEntityBlock {
	
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	
	private Function<Properties, ? extends PerhipheralBlock<T>> super_constructor;

	public PerhipheralBlock(Properties properties, Function<Properties, ? extends PerhipheralBlock<T>> super_constructor) {
		super(properties);
		this.super_constructor = super_constructor;
		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
			Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (itemStack.getItem() == MCIOItems.WRENCH) {
			if (!level.isClientSide()) {
				WrenchInfoComponent component = itemStack.get(MCIOComponents.WRENCH_DATA);
				if ((component != null && component.canSelectController(blockPos)) || component == null) {
					itemStack.set(MCIOComponents.WRENCH_DATA, new WrenchInfoComponent(blockPos));
					return InteractionResult.SUCCESS_SERVER;
				}
				return InteractionResult.FAIL;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
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
