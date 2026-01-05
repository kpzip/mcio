package xyz.kpzip.mcio.blockentity.renderer;

import java.util.List;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.block.MCIOBlocks;
import xyz.kpzip.mcio.block.peripheral.PeripheralBlock;
import xyz.kpzip.mcio.blockentity.peripheral.PeripheralBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.state.PeripheralControllerRenderState;
import xyz.kpzip.mcio.item.component.WrenchState;

public class PeripheralControllerRenderer<T extends PeripheralBlockEntity> extends PeripheralRenderer<T, PeripheralControllerRenderState> {

	public PeripheralControllerRenderer(Context ctx) {
		super(ctx);
	}

	@Override
	public PeripheralControllerRenderState createRenderState() {
		return new PeripheralControllerRenderState();
	}
	
	@Override
	protected void getText(List<Component> lines, WrenchState component,
			PeripheralControllerRenderState blockEntityRenderState) {
		BlockState s = component.getSelectedController().state();
		PeripheralBlock block = (PeripheralBlock) s.getBlock();
		BlockPos pos = blockEntityRenderState.blockPos;
		long inputs_needed = block.maxSelectable(pos, s, MCIOBlocks.PERIPHERAL_INPUT) - component.numSelected(MCIOBlocks.PERIPHERAL_INPUT);
		long outputs_needed = block.maxSelectable(pos, s, MCIOBlocks.PERIPHERAL_OUTPUT) - component.numSelected(MCIOBlocks.PERIPHERAL_OUTPUT);
		long bidirects_needed = block.maxSelectable(pos, s, MCIOBlocks.PERIPHERAL_BIDIRECTIONAL) - component.numSelected(MCIOBlocks.PERIPHERAL_BIDIRECTIONAL);
		boolean complete = component.isComplete();
		lines.add(block.getBlockPerhipheralName());
		lines.add(complete ? Component.translatable("gui.mcio.peripheral_selector.complete") : Component.translatable("gui.mcio.peripheral_selector.incomplete"));
		
		if (inputs_needed > 0) {
			lines.add(Component.translatableEscape("gui.mcio.peripheral_selector.missing_input", inputs_needed));
		}
		if (outputs_needed > 0) {
			lines.add(Component.translatableEscape("gui.mcio.peripheral_selector.missing_output", outputs_needed));
		}
		if (bidirects_needed > 0) {
			lines.add(Component.translatableEscape("gui.mcio.peripheral_selector.missing_bidirect", bidirects_needed));
		}
		
	}

}
