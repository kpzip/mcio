package xyz.kpzip.mcio.item.component;

import java.util.List;

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

public class WrenchInfoComponent {
	
	public static final Codec<WrenchInfoComponent> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.list(SelectedBlockData.CODEC).fieldOf("selected_blocks").forGetter(WrenchInfoComponent::getSelectedBlocks),
					SelectedBlockData.CODEC.fieldOf("selected_controller").forGetter(WrenchInfoComponent::getSelectedController)
				)
				.apply(instance, WrenchInfoComponent::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, WrenchInfoComponent> STREAM_CODEC = StreamCodec.composite(
			SelectedBlockData.STREAM_CODEC.apply(ByteBufCodecs.list()), WrenchInfoComponent::getSelectedBlocks, 
			SelectedBlockData.STREAM_CODEC, WrenchInfoComponent::getSelectedController, 
			WrenchInfoComponent::new);
	
	
	private final List<SelectedBlockData> selectedBlocks;
	@Nullable
	private final SelectedBlockData selectedController;
	
	public WrenchInfoComponent() {
		this(null);
	}
	
	public WrenchInfoComponent(List<SelectedBlockData> selectedBlocks, SelectedBlockData selectedController) {
		this.selectedBlocks = selectedBlocks;
		this.selectedController = selectedController;
	}
	
	public WrenchInfoComponent(SelectedBlockData selectedController) {
		this(ImmutableList.of(), selectedController);
	}
	
	public WrenchInfoComponent(BlockPos pos, BlockState state) {
		this(ImmutableList.of(), new SelectedBlockData(pos, state));
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
	
	public static record SelectedBlockData(BlockPos pos, BlockState state) {
		public static final Codec<SelectedBlockData> CODEC = RecordCodecBuilder.create(
				instance -> instance.group(
						BlockPos.CODEC.fieldOf("pos").forGetter(SelectedBlockData::pos),
						BlockState.CODEC.fieldOf("state").forGetter(SelectedBlockData::state)
					)
					.apply(instance, SelectedBlockData::new));
		public static final StreamCodec<RegistryFriendlyByteBuf, SelectedBlockData> STREAM_CODEC = StreamCodec.composite(
				BlockPos.STREAM_CODEC, SelectedBlockData::pos, 
				ByteBufCodecs.fromCodec(BlockState.CODEC), SelectedBlockData::state,
				SelectedBlockData::new);
		
	}

}
