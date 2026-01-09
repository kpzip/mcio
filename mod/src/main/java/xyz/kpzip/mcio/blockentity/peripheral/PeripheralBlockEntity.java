package xyz.kpzip.mcio.blockentity.peripheral;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralComponentBlockEntity;

public abstract class PeripheralBlockEntity extends PeripheralComponentBlockEntity implements MenuProvider {

	public PeripheralBlockEntity(BlockEntityType<? extends PeripheralComponentBlockEntity> blockEntityType, BlockPos blockPos, BlockState blockState) {
		super(blockEntityType, blockPos, blockState);
	}
	
	@Override
	public Component getDisplayName() {
		return Component.translatable(getBlockState().getBlock().getDescriptionId());
	}
	
	@Override
	public int getOutlineColor() {
		return DyeColor.LIME.getTextColor();
	}
	
	public abstract boolean isMultiblock();
	

}
