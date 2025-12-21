/**
 * 
 */
package xyz.kpzip.mcio.block;

import java.util.OptionalInt;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.blockentity.MCUInterfaceBlockEntity;

/**
 * 
 * @author kpzip
 * 
 */
public class MCUInterface extends BaseEntityBlock {

	public MCUInterface(BlockBehaviour.Properties properties) {
		super(properties);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new MCUInterfaceBlockEntity(blockPos, blockState);
	}
	
	@Override
	protected InteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
		if (!level.isClientSide()) {
			MenuProvider provider = blockState.getMenuProvider(level, blockPos);
			
			if (provider != null) {
				player.openMenu(provider);
			}
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(MCUInterface::new);
	}
	
	
	

}
