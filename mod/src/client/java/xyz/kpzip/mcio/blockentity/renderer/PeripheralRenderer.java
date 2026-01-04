package xyz.kpzip.mcio.blockentity.renderer;

import java.util.ArrayList;
import java.util.List;

import org.joml.Quaternionf;
import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.model.MCIOModelLayers;
import xyz.kpzip.mcio.blockentity.renderer.model.PerhipheralComponentModel;
import xyz.kpzip.mcio.blockentity.renderer.state.PeripheralRenderState;
import xyz.kpzip.mcio.item.MCIOItems;
import xyz.kpzip.mcio.item.component.MCIOComponents;
import xyz.kpzip.mcio.item.component.WrenchState;

@Environment(EnvType.CLIENT)
public abstract class PeripheralRenderer<T extends PeripheralComponentBlockEntity, S extends PeripheralRenderState> implements BlockEntityRenderer<T, S> {
	
	public static final Material OVERLAY_RESOURCE_LOCATION = Sheets.BLOCK_ENTITIES_MAPPER.apply(Identifier.tryBuild(MCIO.MOD_ID, "overlay/wrench_selection_overlay"));
	private final BlockEntityRendererProvider.Context ctx;
	private final PerhipheralComponentModel model;
	
	public PeripheralRenderer(BlockEntityRendererProvider.Context ctx) {
		this.ctx = ctx;
		this.model = new PerhipheralComponentModel(ctx.bakeLayer(MCIOModelLayers.WRENCH_SELECT_OVERLAY));
	}
	
	@Override
	public void extractRenderState(T blockEntity, S blockEntityRenderState, float f,
			Vec3 vec3, @Nullable CrumblingOverlay crumblingOverlay) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, blockEntityRenderState, f, vec3, crumblingOverlay);
		blockEntityRenderState.setColorVal(blockEntity.getOutlineColor());
	}
	
	protected abstract void getText(List<Component> lines, WrenchState component, S blockEntityRenderState);

	@Override
	public void submit(S blockEntityRenderState, PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		LocalPlayer p = Minecraft.getInstance().player;
		ItemStack mh = p.getMainHandItem();
		WrenchState component = mh.get(MCIOComponents.WRENCH_DATA);
		BlockPos rPos = blockEntityRenderState.blockPos;
		float text_scale = 1/18f * 1/3;
		float height = this.ctx.font().lineHeight;
		final List<Component> lines = new ArrayList<Component>(6);
		
		if (component != null && (mh.getItem() == MCIOItems.WRENCH && 
				(component.getSelectedBlocks().stream().anyMatch((sel) -> sel.pos().equals(rPos))) 
				|| (component.getSelectedController() != null && rPos.equals(component.getSelectedController().pos())))) {
			this.getText(lines, component, blockEntityRenderState);
			poseStack.pushPose();
			poseStack.translate(0.5, 0.5, 0.5);
			PerhipheralComponentModel.State state = new PerhipheralComponentModel.State();
			RenderType renderType = OVERLAY_RESOURCE_LOCATION.renderType(RenderTypes::armorTranslucent);
			submitNodeCollector.submitModel(model, state, poseStack, renderType, 255,
					OverlayTexture.NO_OVERLAY,
					blockEntityRenderState.getColorVal(),
					this.ctx.materials().get(OVERLAY_RESOURCE_LOCATION),
					0,
					null);
			
			poseStack.popPose();
			poseStack.pushPose();
			
			poseStack.translate(0.5, 1.5, 0.5);
			poseStack.scale(text_scale, text_scale, text_scale);
			
			Camera camera = this.ctx.entityRenderer().camera;
			poseStack.mulPose(new Quaternionf().rotationYXZ(
					(float) (Math.PI / 180.0) * -(camera.yRot() + 180.0F),
					(float) (Math.PI / 180.0) * -(camera.xRot() + 180.0F),
					0.0F
				));
			
			for (Component c : lines) {
				float width = ctx.font().width(c);
				submitNodeCollector.submitText(poseStack, -width/2, -4.0F, FormattedCharSequence.forward(c.getString(), Style.EMPTY), false, Font.DisplayMode.SEE_THROUGH,
					blockEntityRenderState.lightCoords,
					0xffffffff,
					0,
					0);
//				poseStack.pushPose();
				poseStack.translate(0, height, 0);
			}
			for (int i = 0; i < lines.size(); i++) {
//				poseStack.popPose();
			}
			poseStack.popPose();
		}
		
	}
	
	

}
