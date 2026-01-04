package xyz.kpzip.mcio.block.peripheral.mutiblock;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.block.peripheral.PeripheralBlock;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchState;

public abstract class PeripheralMultiblockComponent extends BaseEntityBlock {

	public PeripheralMultiblockComponent(Block.Properties properties) {
		super(properties);
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
			Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (itemStack.getItem() == MCIOItems.WRENCH) {
			if (!level.isClientSide()) {
				WrenchState component = itemStack.get(MCIOComponents.WRENCH_DATA);
				BlockState controllerState = null;
				if (component != null && component.getSelectedController() != null) {
					controllerState = component.getSelectedController().state();
				}
				if (component != null && component.isSelected(blockPos)) {
					itemStack.set(MCIOComponents.WRENCH_DATA, component.remove(blockPos));
					return InteractionResult.SUCCESS_SERVER;
				}
				else if (component != null && component.canSelect(blockPos) && controllerState != null && controllerState.getBlock() instanceof PeripheralBlock b && b.canSelect(blockPos, blockState, level, player, component, itemStack)) {
					itemStack.set(MCIOComponents.WRENCH_DATA, new WrenchState(new ImmutableList.Builder<WrenchState.SelectedBlockData>().addAll(component.getSelectedBlocks()).add(new WrenchState.SelectedBlockData(blockPos, blockState, component.nextAvailable(b.getSelectableStates().get(this)))).build(), component.getSelectedController()));
					return InteractionResult.SUCCESS_SERVER;
				}
				return InteractionResult.FAIL;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

}
