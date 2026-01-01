package xyz.kpzip.mcio.blockentity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import xyz.kpzip.mcio.MCIO;
import xyz.kpzip.mcio.blockentity.perhipheral.component.PerhipheralComponentBlockEntity;
import xyz.kpzip.mcio.blockentity.renderer.model.MCIOModelLayers;
import xyz.kpzip.mcio.blockentity.renderer.model.PerhipheralComponentModel;
import xyz.kpzip.mcio.blockentity.renderer.state.PerhipheralComponentRendererState;
import xyz.kpzip.mcio.item.MCIOItems;

@Environment(EnvType.CLIENT)
public class PerhipheralComponentRenderer<T extends PerhipheralComponentBlockEntity> implements BlockEntityRenderer<T, PerhipheralComponentRendererState> {
	
	public static final Material OVERLAY_RESOURCE_LOCATION = Sheets.BLOCK_ENTITIES_MAPPER.apply(Identifier.tryBuild(MCIO.MOD_ID, "overlay/wrench_selection_overlay"));
	private final BlockEntityRendererProvider.Context ctx;
	private final PerhipheralComponentModel model;
	
	public PerhipheralComponentRenderer(BlockEntityRendererProvider.Context ctx) {
		this.ctx = ctx;
		this.model = new PerhipheralComponentModel(ctx.bakeLayer(MCIOModelLayers.WRENCH_SELECT_OVERLAY));
	}
	
	@Override
	public PerhipheralComponentRendererState createRenderState() {
		return new PerhipheralComponentRendererState();
	}

	@Override
	public void submit(PerhipheralComponentRendererState blockEntityRenderState, PoseStack poseStack,
			SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		LocalPlayer p = Minecraft.getInstance().player;
//		ItemStack lh = p.getItemHeldByArm(HumanoidArm.LEFT);
//		ItemStack rh = p.getItemHeldByArm(HumanoidArm.RIGHT);
		ItemStack mh = p.getMainHandItem();
		
		String text = "miso";
		float width = ctx.font().width(text);
		
		if (mh.getItem() == MCIOItems.WRENCH) {
			poseStack.pushPose();
			poseStack.translate(0.5, 0.5, 0.5);
			PerhipheralComponentModel.State state = new PerhipheralComponentModel.State();
			RenderType renderType = OVERLAY_RESOURCE_LOCATION.renderType(RenderTypes::armorTranslucent);
			submitNodeCollector.submitModel(model, state, poseStack, renderType, 255,
					OverlayTexture.NO_OVERLAY,
					DyeColor.LIME.getTextureDiffuseColor(),
					this.ctx.materials().get(OVERLAY_RESOURCE_LOCATION),
					0,
					null);
			
			poseStack.popPose();
			poseStack.pushPose();
			float text_scale = 1/18f * 1/4;
			poseStack.translate(0.5, 1, 0.5);
			poseStack.mulPose(Axis.XP.rotationDegrees(90));
			poseStack.scale(text_scale, text_scale, text_scale);
			submitNodeCollector.submitText(poseStack, -width/2, -4.0f, FormattedCharSequence.forward(text, Style.EMPTY), false, Font.DisplayMode.SEE_THROUGH,
					blockEntityRenderState.lightCoords,
					0xffffffff,
					0,
					0);
			poseStack.popPose();
		}
		
	}
	
	

}
