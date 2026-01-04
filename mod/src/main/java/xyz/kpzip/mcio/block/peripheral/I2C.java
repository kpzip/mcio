package xyz.kpzip.mcio.block.peripheral;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralMultiblockComponent;
import xyz.kpzip.mcio.block.peripheral.state.I2CBlockStates;
import xyz.kpzip.mcio.block.peripheral.state.PeripheralType;
import xyz.kpzip.mcio.blockentity.peripheral.I2CBlockEntity;

public class I2C extends PeripheralBlock {
	
	public static final EnumProperty<I2CBlockStates> PART = EnumProperty.create("part", I2CBlockStates.class);
	
	public static final Map<PeripheralMultiblockComponent, PeripheralType[]> SELECTABLE_STATES = ImmutableMap.of((PeripheralMultiblockComponent) MCIOBlocks.PERHIPHERAL_BIDIRECTIONAL, new I2CBlockStates[] {I2CBlockStates.SDA, I2CBlockStates.SCL});

	public I2C(Properties properties) {
		super(properties, I2C::new);
		this.registerDefaultState(this.defaultBlockState().setValue(PART, I2CBlockStates.SINGLE));
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new I2CBlockEntity(blockPos, blockState);
	}
	
	@Override
	protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(PART);
	}

	@Override
	public Component getBlockPerhipheralName() {
		return Component.translatable("gui.mcio.perhipheral_selector.i2c");
	}

	@Override
	public Map<PeripheralMultiblockComponent, PeripheralType[]> getSelectableStates() {
		return SELECTABLE_STATES;
	}

}
