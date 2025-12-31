package xyz.kpzip.mcio.blockentity.renderer.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import xyz.kpzip.mcio.mixin.client.ModelLayersAccessor;

@Environment(EnvType.CLIENT)
public class MCIOModelLayers {
	
	public static final ModelLayerLocation WRENCH_SELECT_OVERLAY = ModelLayersAccessor.mcio$register("wrench_select_overlay");

	public static void init() {}
	
}
