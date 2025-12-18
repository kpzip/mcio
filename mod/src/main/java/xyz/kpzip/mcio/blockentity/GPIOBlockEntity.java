package xyz.kpzip.mcio.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.screen.handler.GPIOScreenHandler;

public class GPIOBlockEntity extends BlockEntity implements MenuProvider {

	public GPIOBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MCIOBlockEntities.GPIO_BLOCK_ENTITY, blockPos, blockState);
	}

	@Override
	public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
		return new GPIOScreenHandler(i, inventory);
	}

	@Override
	public Component getDisplayName() {
		
		return Component.translatable(getBlockState().getBlock().getDescriptionId());
	}
	
	
	
	
	
	
	
	

}
