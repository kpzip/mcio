package xyz.kpzip.mcio.block.perhipheral.mutiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.item.MCIOItems;

public abstract class PerhipheralMultiblockComponent extends Block {

	public PerhipheralMultiblockComponent(Properties properties) {
		super(properties);
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos,
			Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (itemStack.getItem() == MCIOItems.WRENCH) {
			if (!level.isClientSide()) {
				
				return InteractionResult.SUCCESS_SERVER;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.FAIL;
	}

}
