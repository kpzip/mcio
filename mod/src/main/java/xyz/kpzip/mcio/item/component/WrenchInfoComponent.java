package xyz.kpzip.mcio.item.component;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public class WrenchInfoComponent {
	
	private List<BlockPos> selectedBlocks = new ArrayList<BlockPos>();
	public static final Codec<WrenchInfoComponent> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
					Codec.list(BlockPos.CODEC).fieldOf("selected_blocks").forGetter(WrenchInfoComponent::getSelectedBlocks),
					BlockPos.CODEC.fieldOf("selected_controller").forGetter(WrenchInfoComponent::getSelectedController)
				)
				.apply(instance, WrenchInfoComponent::new));
	public static final StreamCodec<RegistryFriendlyByteBuf, WrenchInfoComponent> STREAM_CODEC = StreamCodec.composite(
			BlockPos.STREAM_CODEC.apply(ByteBufCodecs.list()), WrenchInfoComponent::getSelectedBlocks, BlockPos.STREAM_CODEC, WrenchInfoComponent::getSelectedController, WrenchInfoComponent::new);
	@Nullable
	private BlockPos selectedController = null;
	
	public WrenchInfoComponent() {
		
	}
	
	public WrenchInfoComponent(List<BlockPos> selectedBlocks, BlockPos selectedController) {
		this.selectedBlocks = selectedBlocks;
		this.selectedController = selectedController;
	}
	
	public boolean canSelect(BlockPos pos) {
		return selectedController == null || selectedController.distManhattan(pos) == 1 || 
				selectedBlocks.stream().anyMatch((pos2) -> pos.distManhattan(pos2) == 1);
	}
	
	public void selectController(BlockPos pos) {
		this.selectedController = pos;
	}
	
	@Nullable
	public BlockPos getSelectedController() {
		return this.selectedController;
	}
	
	public List<BlockPos> getSelectedBlocks() {
		return this.selectedBlocks;
	}

}
