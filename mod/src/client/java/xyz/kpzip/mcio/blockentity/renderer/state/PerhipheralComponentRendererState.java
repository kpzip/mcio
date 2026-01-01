package xyz.kpzip.mcio.blockentity.renderer.state;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

@Environment(EnvType.CLIENT)
public class PerhipheralComponentRendererState extends BlockEntityRenderState {
	
	private int colorVal = -1;
	
	public PerhipheralComponentRendererState() {
	}
	
	public void setColorVal(int colorVal) {
		this.colorVal = colorVal;
	}
	
	public int getColorVal() {
		return colorVal;
	}

}
