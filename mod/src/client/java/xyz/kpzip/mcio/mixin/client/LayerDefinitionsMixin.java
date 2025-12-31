package xyz.kpzip.mcio.mixin.client;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import xyz.kpzip.mcio.blockentity.renderer.model.MCIOModelLayers;
import xyz.kpzip.mcio.blockentity.renderer.model.PerhipheralComponentModel;

@Mixin(LayerDefinitions.class)
public abstract class LayerDefinitionsMixin {
	
	@Inject(method="createRoots", at=@At(value="INVOKE", target="Lcom/google/common/collect/ImmutableMap$Builder;put(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableMap$Builder;", ordinal=0), locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	private static void injectedHandlerMethod(CallbackInfoReturnable<Map<ModelLayerLocation, LayerDefinition>> info, Builder<ModelLayerLocation, LayerDefinition> builder) {
		builder.put(MCIOModelLayers.WRENCH_SELECT_OVERLAY, PerhipheralComponentModel.createBodyLayer());
	}

	

}
