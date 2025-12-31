/**
 * 
 */
package xyz.kpzip.mcio.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import xyz.kpzip.mcio.screen.handler.GPIOScreenHandler;

/**
 * 
 * @author kpzip
 * 
 */
public class GPIOScreen extends AbstractContainerScreen<GPIOScreenHandler> {
	
	private static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/gui/container/dispenser.png");
	
	/**
	 * @param abstractContainerMenu
	 * @param inventory
	 * @param component
	 */
	public GPIOScreen(GPIOScreenHandler abstractContainerMenu, Inventory inventory, Component component) {
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
