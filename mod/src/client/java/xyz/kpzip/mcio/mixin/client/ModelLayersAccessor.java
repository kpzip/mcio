package xyz.kpzip.mcio.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;

@Mixin(ModelLayers.class)
public interface ModelLayersAccessor {
	
	@Invoker("register")
	public static ModelLayerLocation mcio$register(String name) {
		throw new AssertionError("unreachable");
	}
	
	
}