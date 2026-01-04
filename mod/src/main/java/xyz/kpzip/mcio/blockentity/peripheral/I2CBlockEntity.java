package xyz.kpzip.mcio.blockentity.peripheral;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import xyz.kpzip.mcio.blockentity.MCIOBlockEntities;

public class I2CBlockEntity extends PeripheralBlockEntity {
	
	@Nullable
	private BlockPos controllerPos;
	@Nullable
	private BlockPos sdaPos;
	@Nullable
	private BlockPos sclPos;

	public I2CBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(MCIOBlockEntities.I2C_PERHIPHERAL_BLOCK_ENTITY, blockPos, blockState);
		this.controllerPos = null;
		this.sdaPos = null;
		this.sclPos = null;
	}

	@Override
	public AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
		return null;
	}
	
	public void initializeMultiblock(BlockPos controllerPos, BlockPos sdaPos, BlockPos sclPos) {
		this.controllerPos = controllerPos;
		this.sdaPos = sdaPos;
		this.sclPos = sclPos;
	}
	
	public void deinitializeMultiblock() {
		this.controllerPos = null;
		this.sdaPos = null;
		this.sclPos = null;
	}
	
	@Override
	protected void saveAdditional(ValueOutput valueOutput) {	
		valueOutput.storeNullable("controllerPos", BlockPos.CODEC, this.controllerPos);
		valueOutput.storeNullable("sdaPos", BlockPos.CODEC, this.sdaPos);
		valueOutput.storeNullable("sclPos", BlockPos.CODEC, this.sclPos);
		
		super.saveAdditional(valueOutput);
	}
	
	@Override
	protected void loadAdditional(ValueInput valueInput) {
		super.loadAdditional(valueInput);
		
		this.sclPos = valueInput.read("sclPos", BlockPos.CODEC).orElse(null);
		this.sdaPos = valueInput.read("sdaPos", BlockPos.CODEC).orElse(null);
		this.controllerPos = valueInput.read("controllerPos", BlockPos.CODEC).orElse(null);
	}

}
