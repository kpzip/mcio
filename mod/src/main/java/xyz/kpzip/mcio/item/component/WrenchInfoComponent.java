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

public class WrenchInfoComponent {
	
	public static final Codec<WrenchInfoComponent> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.list(BlockPos.CODEC).fieldOf("selected_blocks").forGetter(WrenchInfoComponent::getSelectedBlocks),
					BlockPos.CODEC.fieldOf("selected_controller").forGetter(WrenchInfoComponent::getSelectedController)
				)
				.apply(instance, WrenchInfoComponent::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, WrenchInfoComponent> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC.apply(ByteBufCodecs.list()), WrenchInfoComponent::getSelectedBlocks, BlockPos.STREAM_CODEC, WrenchInfoComponent::getSelectedController, WrenchInfoComponent::new);
	
	
	private final List<BlockPos> selectedBlocks;
	@Nullable
	private final BlockPos selectedController;
	
	public WrenchInfoComponent() {
		this(null);
	}
	
	public WrenchInfoComponent(List<BlockPos> selectedBlocks, BlockPos selectedController) {
		this.selectedBlocks = selectedBlocks;
		this.selectedController = selectedController;
	}
	
	public WrenchInfoComponent(BlockPos selectedController) {
		this(ImmutableList.of(), selectedController);
	}
	
	public boolean canSelect(BlockPos pos) {
		return selectedController != null && (selectedController.distManhattan(pos) == 1 || 
				selectedBlocks.stream().anyMatch((pos2) -> pos.distManhattan(pos2) == 1));
	}
	
	public boolean canSelectController(BlockPos pos) {
		return selectedController == null;
	}
	
	public boolean isSelected(BlockPos pos) {
		return selectedBlocks.stream().anyMatch((pos2) -> pos.equals(pos2));
	}
	
	public boolean isSelectedController(BlockPos pos) {
		return selectedController != null && selectedController.equals(pos);
	}
	
	@Nullable
	public BlockPos getSelectedController() {
		return this.selectedController;
	}
	
	public List<BlockPos> getSelectedBlocks() {
		return this.selectedBlocks;
	}

}
