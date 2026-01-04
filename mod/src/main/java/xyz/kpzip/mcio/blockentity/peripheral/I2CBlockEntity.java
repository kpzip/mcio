package xyz.kpzip.mcio.blockentity.peripheral;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class I2CBlockEntity extends PeripheralBlockEntity {

	public I2CBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MCIOBlockEntities.I2C_PERHIPHERAL_BLOCK_ENTITY, blockPos, blockState);
	}

	@Override
	public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
		return null;
	}

}
