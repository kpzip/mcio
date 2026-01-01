package xyz.kpzip.mcio.block.perhipheral.mutiblock;

import com.google.common.collect.ImmutableList;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchInfoComponent;

public abstract class PerhipheralMultiblockComponent extends BaseEntityBlock {

	public PerhipheralMultiblockComponent(Block.Properties properties) {
		super(properties);
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
			Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (itemStack.getItem() == MCIOItems.WRENCH) {
			if (!level.isClientSide()) {
				WrenchInfoComponent component = itemStack.get(MCIOComponents.WRENCH_DATA);
				if (component != null && component.isSelected(blockPos)) {
					itemStack.set(MCIOComponents.WRENCH_DATA, new WrenchInfoComponent(new ImmutableList.Builder<BlockPos>().addAll(component.getSelectedBlocks().stream().filter((pos2) -> !pos2.equals(blockPos)).iterator()).build(), component.getSelectedController()));
					return InteractionResult.SUCCESS_SERVER;
				}
				else if (component != null && component.canSelect(blockPos)) {
					itemStack.set(MCIOComponents.WRENCH_DATA, new WrenchInfoComponent(new ImmutableList.Builder<BlockPos>().addAll(component.getSelectedBlocks()).add(blockPos).build(), component.getSelectedController()));
					return InteractionResult.SUCCESS_SERVER;
				}
				return InteractionResult.FAIL;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		// TODO Auto-generated method stub
		return null;
	}

}
