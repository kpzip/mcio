package xyz.kpzip.mcio.item.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.block.peripheral.PeripheralBlock;
import xyz.kpzip.mcio.block.peripheral.state.PeripheralType;
import xyz.kpzip.mcio.block.peripheral.state.PeripheralTypes;
import xyz.kpzip.mcio.block.peripheral.state.UnitState;

public class WrenchState {
	
	public static final Codec<WrenchState> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.list(SelectedBlockData.CODEC).fieldOf("selected_blocks").forGetter(WrenchState::getSelectedBlocks),
					SelectedBlockData.CODEC.fieldOf("selected_controller").forGetter(WrenchState::getSelectedController)
				)
				.apply(instance, WrenchState::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, WrenchState> STREAM_CODEC = StreamCodec.composite(
			SelectedBlockData.STREAM_CODEC.apply(ByteBufCodecs.list()), WrenchState::getSelectedBlocks, 
			SelectedBlockData.STREAM_CODEC, WrenchState::getSelectedController, 
			WrenchState::new);
	
	
	private final List<SelectedBlockData> selectedBlocks;
	@Nullable
	private final SelectedBlockData selectedController;
	
	public WrenchState() {
		this(null);
	}
	
	public WrenchState(List<SelectedBlockData> selectedBlocks, SelectedBlockData selectedController) {
		this.selectedBlocks = selectedBlocks;
		this.selectedController = selectedController;
	}
	
	public WrenchState(SelectedBlockData selectedController) {
		this(ImmutableList.of(), selectedController);
	}
	
	public WrenchState(BlockPos pos, BlockState state) {
		this(ImmutableList.of(), new SelectedBlockData(pos, state, UnitState.INSTANCE));
	}
	
	public boolean canSelect(BlockPos pos) {
		return selectedController != null && (selectedController.pos().distManhattan(pos) == 1 || 
				selectedBlocks.stream().anyMatch((pos2) -> pos.distManhattan(pos2.pos()) == 1));
	}
	
	public boolean canSelectController(BlockPos pos) {
		return selectedController == null;
	}
	
	public boolean isSelected(BlockPos pos) {
		return selectedBlocks.stream().anyMatch((sel) -> pos.equals(sel.pos()));
	}
	
	public boolean isSelectedController(BlockPos pos) {
		return selectedController != null && selectedController.pos().equals(pos);
	}
	
	@Nullable
	public SelectedBlockData getSelectedController() {
		return this.selectedController;
	}
	
	public List<SelectedBlockData> getSelectedBlocks() {
		return this.selectedBlocks;
	}
	
	// Excludes the controller
	public long numSelected(Block block) {
		return this.selectedBlocks.stream().filter((sel) -> sel.state().getBlock() == block).count();
	}
	
	public boolean stillValid(Level l) {
		return this.selectedBlocks.stream().allMatch((sel) -> l.getBlockState(sel.pos()).equals(sel.state())) && l.getBlockState(this.selectedController.pos()).equals(this.selectedController.state());
	}
	
	public boolean isComplete() {
		if (this.selectedController == null) {
			return false;
		}
		BlockPos pos = this.selectedController.pos();
		BlockState state = this.selectedController.state();
		PeripheralBlock block = (PeripheralBlock) state.getBlock();
		long inputs_needed = block.maxSelectable(pos, state, MCIOBlocks.PERHIPHERAL_INPUT) - this.numSelected(MCIOBlocks.PERHIPHERAL_INPUT);
		long outputs_needed = block.maxSelectable(pos, state, MCIOBlocks.PERHIPHERAL_OUTPUT) - this.numSelected(MCIOBlocks.PERHIPHERAL_OUTPUT);
		long bidirects_needed = block.maxSelectable(pos, state, MCIOBlocks.PERHIPHERAL_BIDIRECTIONAL) - this.numSelected(MCIOBlocks.PERHIPHERAL_BIDIRECTIONAL);
		return inputs_needed == 0 && outputs_needed == 0 && bidirects_needed == 0;
	}
	
	public PeripheralType nextAvailable(PeripheralType[] types) {
		int idx = 0;
		if (types.length == 0) {
			MCIO.LOGGER.info("bruh1");
			return UnitState.INSTANCE;
		}
		for (int i = 0; i < this.selectedBlocks.size() && idx < types.length; i++) {
			if (this.selectedBlocks.get(i).selectedType() == types[idx]) {
				idx++;
				continue;
			}
			return types[idx];
		}
		if (types.length > this.selectedBlocks.size()) {
			return types[idx];
		}
		MCIO.LOGGER.info("bruh2");
		return UnitState.INSTANCE;
	}
	
	public WrenchState remove(BlockPos pos) {
		return removeMultiple(pos);
	}
	
	@Nullable
	public WrenchState removeMultiple(BlockPos... positions) {
		if (this.selectedController == null || !Arrays.stream(positions).anyMatch(p -> !Objects.equals(p, this.selectedController.pos))) return null;
		List<SelectedBlockData> newSelection = new ArrayList<SelectedBlockData>(this.selectedBlocks.size() - positions.length);
		// Add all blocks adjacent to the controller
		for (SelectedBlockData selected : this.selectedBlocks) {
			if (this.selectedController.pos().distManhattan(selected.pos) == 1 && Arrays.stream(positions).allMatch(p -> !Objects.equals(p, selected.pos()))) {
				newSelection.add(selected);
			}
		}
		
		boolean modified;
		
		do {
			modified = false;
			for (SelectedBlockData selected : this.selectedBlocks) {
				if (!newSelection.stream().allMatch((s) -> s.pos.distManhattan(selected.pos) != 1) && newSelection.stream().allMatch((s) -> s.pos.distManhattan(selected.pos) != 0) && Arrays.stream(positions).allMatch(p -> !Objects.equals(p, selected.pos()))) {
					newSelection.add(selected);
					modified = true;
				}
			}
		} while (modified);
		return new WrenchState(ImmutableList.copyOf(newSelection), this.selectedController);
	}
	
	public Iterable<SelectedBlockData> dataForType(PeripheralType type) {
		return () -> this.selectedBlocks.stream().filter((data) -> data.selectedType == type).iterator();
	}
	
	public static record SelectedBlockData(BlockPos pos, BlockState state, PeripheralType selectedType) {
		public static final Codec<SelectedBlockData> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
						BlockPos.CODEC.fieldOf("pos").forGetter(SelectedBlockData::pos),
						BlockState.CODEC.fieldOf("state").forGetter(SelectedBlockData::state),
						PeripheralTypes.CODEC.fieldOf("selectedType").forGetter(SelectedBlockData::selectedType)
					)
					.apply(instance, SelectedBlockData::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, SelectedBlockData> STREAM_CODEC = StreamCodec.composite(
				BlockPos.STREAM_CODEC, SelectedBlockData::pos, 
				ByteBufCodecs.fromCodec(BlockState.CODEC), SelectedBlockData::state,
				PeripheralTypes.STREAM_CODEC, SelectedBlockData::selectedType,
				SelectedBlockData::new);
		
	}

}
