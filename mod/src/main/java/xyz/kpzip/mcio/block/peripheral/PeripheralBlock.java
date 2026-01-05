package xyz.kpzip.mcio.block.peripheral;

import java.util.Map;
import java.util.function.Function;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.block.peripheral.state.PeripheralType;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchState;

public abstract class PeripheralBlock extends BaseEntityBlock {
	
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");
	
	private Function<Properties, ? extends PeripheralBlock> super_constructor;

	public PeripheralBlock(Properties properties, Function<Properties, ? extends PeripheralBlock> super_constructor) {
		super(properties);
		this.super_constructor = super_constructor;
		this.registerDefaultState(this.defaultBlockState().setValue(POWERED, false));
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
			Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (itemStack.getItem() == MCIOItems.WRENCH) {
			if (!level.isClientSide()) {
				WrenchState component = itemStack.get(MCIOComponents.WRENCH_DATA);
				if (this.isSelectable(blockState) && ((component != null && component.canSelectController(blockPos)) || component == null)) {
					itemStack.set(MCIOComponents.WRENCH_DATA, new WrenchState(blockPos, blockState));
					return InteractionResult.SUCCESS_SERVER;
				}
				else if (component != null && component.isComplete()) {
					this.createMultiblock(blockState, level, blockPos, player, interactionHand, blockHitResult, itemStack, component, level.getBlockEntity(blockPos));
					itemStack.set(MCIOComponents.WRENCH_DATA, null);
					return InteractionResult.SUCCESS_SERVER;
				}
				return InteractionResult.FAIL;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	protected abstract void createMultiblock(BlockState state, Level level, BlockPos controllerPos, Player player, InteractionHand hand,
			BlockHitResult hitResult, ItemStack wrenchStack, WrenchState wrench, BlockEntity be);
	
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
	
	public int maxSelectable(BlockPos pos, BlockState state, Block block) {
		PeripheralType[] types = this.getSelectablePeripheralComponentStates().get(block);
		return types == null ? 0 : types.length;
	}
	
	public abstract Component getBlockPerhipheralName();
	
	public boolean canSelect(BlockPos pos, BlockState state, Level level, Player player, WrenchState component, ItemStack wrenchStack) {
		return wrenchSelectionHelper(pos, state, level, player, component, wrenchStack, 
				this.maxSelectable(pos, state, MCIOBlocks.PERIPHERAL_INPUT), 
				this.maxSelectable(pos, state, MCIOBlocks.PERIPHERAL_OUTPUT), 
				this.maxSelectable(pos, state, MCIOBlocks.PERIPHERAL_BIDIRECTIONAL));
	}
	
	private static boolean wrenchSelectionHelper(BlockPos pos, BlockState state, Level level, Player player, 
			WrenchState component, ItemStack wrenchStack, int inputMax, int outputMax, int biDirectMax) {
		long inputs = component.numSelected(MCIOBlocks.PERIPHERAL_INPUT);
		long outputs = component.numSelected(MCIOBlocks.PERIPHERAL_OUTPUT);
		long bidirects = component.numSelected(MCIOBlocks.PERIPHERAL_BIDIRECTIONAL);
		if (state.getBlock() == MCIOBlocks.PERIPHERAL_INPUT) {
			return inputs < inputMax;
		}
		if (state.getBlock() == MCIOBlocks.PERIPHERAL_OUTPUT) {
			return outputs < outputMax;
		}
		if (state.getBlock() == MCIOBlocks.PERIPHERAL_BIDIRECTIONAL) {
			return bidirects < biDirectMax;
		}
		if (state.getBlock() == MCIOBlocks.PERIPHERAL_FILLER) {
			return true;
		}
		return false;
		
	}
	
	public abstract Map<? extends Block, PeripheralType[]> getSelectablePeripheralComponentStates();
	
	public abstract boolean isSelectable(BlockState state);

}
