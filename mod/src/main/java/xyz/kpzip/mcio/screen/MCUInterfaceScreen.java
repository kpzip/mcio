/**
 * 
 */
package xyz.kpzip.mcio.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import xyz.kpzip.mcio.screen.handler.MCUInterfaceScreenHandler;

/**
 * 
 * @author kpzip
 * 
 */
public class MCUInterfaceScreen extends AbstractContainerScreen<MCUInterfaceScreenHandler> {
	
	private static final ResourceLocation TEXTURE = ResourceLocation.withDefaultNamespace("textures/gui/container/dispenser.png");
	
	/**
	 * @param abstractContainerMenu
	 * @param inventory
	 * @param component
	 */
	public MCUInterfaceScreen(MCUInterfaceScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
		super(abstractContainerMenu, inventory, component);
	}

	@Override
	protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
		int k = this.leftPos;
		int l = this.topPos;
		guiGraphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, k, l, 0.0F, 0.0F, this.imageWidth, this.imageHeight, 256, 256);
		
	}
	
	@Override
	public void render(GuiGraphics guiGraphics, int i, int j, float f) {
		renderBackground(guiGraphics, i, j, f);
		super.render(guiGraphics, i, j, f);
		renderTooltip(guiGraphics, i, j);
	}

	

	

}
