package xyz.kpzip.mcio.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.state.CameraRenderState;
import xyz.kpzip.mcio.blockentity.perhipheral.component.PerhipheralComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.state.PerhipheralComponentRendererState;

@Environment(EnvType.CLIENT)
public class PerhipheralComponentRenderer<T extends PerhipheralComponentBlockEntity> implements BlockEntityRenderer<T, PerhipheralComponentRendererState> {
	
	private BlockEntityRendererProvider.Context ctx;
	
	public PerhipheralComponentRenderer(BlockEntityRendererProvider.Context ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public PerhipheralComponentRendererState createRenderState() {
		return new PerhipheralComponentRendererState();
	}

	@Override
	public void submit(PerhipheralComponentRendererState blockEntityRenderState, PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		
		
	}
	
	

}
