package xyz.kpzip.mcio.block.peripheral;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralFiller;
import xyz.kpzip.mcio.block.peripheral.mutiblock.PeripheralMultiblockComponent;
import xyz.kpzip.mcio.block.peripheral.state.I2CBlockStates;
import xyz.kpzip.mcio.block.peripheral.state.PeripheralType;
import xyz.kpzip.mcio.block.peripheral.state.UnitState;
import xyz.kpzip.mcio.blockentity.peripheral.I2CBlockEntity;
import xyz.kpzip.mcio.item.component.WrenchState;

public class I2C extends PeripheralBlock {
	
	public static final EnumProperty<I2CBlockStates> PART = EnumProperty.create("part", I2CBlockStates.class);
	
	public static final Map<PeripheralMultiblockComponent, PeripheralType[]> SELECTABLE_STATES = ImmutableMap.of((PeripheralMultiblockComponent) MCIOBlocks.PERIPHERAL_BIDIRECTIONAL, new PeripheralType[] {I2CBlockStates.SDA, I2CBlockStates.SCL});

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
		return Component.translatable("gui.mcio.peripheral_selector.i2c");
	}

	@Override
	public Map<PeripheralMultiblockComponent, PeripheralType[]> getSelectablePeripheralComponentStates() {
		return SELECTABLE_STATES;
	}
	
	@Override
	public boolean isSelectable(BlockState state) {
		return state.getValue(PART) == I2CBlockStates.SINGLE;
	}
	
	@Override
	protected void createMultiblock(BlockState state, Level level, BlockPos controllerPos, Player player, InteractionHand hand,
			BlockHitResult hitResult, ItemStack wrenchStack, WrenchState wrench, BlockEntity be) {
		I2CBlockEntity controllerBlockEntity = (I2CBlockEntity) be;
		WrenchState.SelectedBlockData sdaData = wrench.dataForType(I2CBlockStates.SDA).iterator().next();
		WrenchState.SelectedBlockData sclData = wrench.dataForType(I2CBlockStates.SCL).iterator().next();
		List<WrenchState.SelectedBlockData> fillerData = ImmutableList.copyOf(wrench.dataForType(UnitState.INSTANCE));
		level.setBlock(sdaData.pos(), this.getStateForPlacement(new BlockPlaceContext(level, player, hand, wrenchStack, hitResult)).setValue(PART, I2CBlockStates.SDA), UPDATE_ALL_IMMEDIATE);
		level.setBlock(sclData.pos(), this.getStateForPlacement(new BlockPlaceContext(level, player, hand, wrenchStack, hitResult)).setValue(PART, I2CBlockStates.SCL), UPDATE_ALL_IMMEDIATE);
		level.setBlock(controllerPos, state.setValue(PART, I2CBlockStates.CONTROLLER), UPDATE_ALL_IMMEDIATE);
		for (WrenchState.SelectedBlockData filler : fillerData) {
			level.setBlock(filler.pos(), filler.state().setValue(PeripheralFiller.IS_MULTIBLOCK, true), UPDATE_ALL_IMMEDIATE);
		}
		I2CBlockEntity sdaBlockEntity = (I2CBlockEntity) level.getBlockEntity(sdaData.pos());
		I2CBlockEntity sclBlockEntity = (I2CBlockEntity) level.getBlockEntity(sclData.pos());
		
		Iterator<BlockPos> fillerPositions = fillerData.stream().map(WrenchState.SelectedBlockData::pos).iterator();
		
		controllerBlockEntity.initializeMultiblock(controllerPos, sdaData.pos(), sclData.pos(), fillerPositions);
		sdaBlockEntity.initializeMultiblock(controllerPos, sdaData.pos(), sclData.pos(), fillerPositions);
		sclBlockEntity.initializeMultiblock(controllerPos, sdaData.pos(), sclData.pos(), fillerPositions);
	}

}
