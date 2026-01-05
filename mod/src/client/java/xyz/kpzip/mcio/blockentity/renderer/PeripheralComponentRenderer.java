package xyz.kpzip.mcio.blockentity.renderer;

import java.util.List;
import java.util.Objects;

import org.jspecify.annotations.Nullable;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.state.PeripheralComponentRenderState;
import xyz.kpzip.mcio.item.component.WrenchState;

public class PeripheralComponentRenderer<T extends PeripheralComponentBlockEntity> extends PeripheralRenderer<T, PeripheralComponentRenderState> {

	public PeripheralComponentRenderer(Context ctx) {
		super(ctx);
	}

	@Override
	public PeripheralComponentRenderState createRenderState() {
		return new PeripheralComponentRenderState();
	}
	
	@Override
	public void extractRenderState(T blockEntity, PeripheralComponentRenderState blockEntityRenderState, float f,
			Vec3 vec3, @Nullable CrumblingOverlay crumblingOverlay) {
		super.extractRenderState(blockEntity, blockEntityRenderState, f, vec3, crumblingOverlay);
		
	}
	
	@Override
	protected void getText(List<Component> lines, WrenchState component, PeripheralComponentRenderState blockEntityRenderState) {
		WrenchState.SelectedBlockData selectedBlockData = component.getSelectedBlocks().stream().filter((data) -> Objects.equals(data.pos(), blockEntityRenderState.blockPos)).findFirst().get();
		Component name = selectedBlockData.selectedType().getName();
		if (name == null) return;
		lines.add(Component.translatable("gui.mcio.peripheral_selector.role"));
		lines.add(name);
	}

}
