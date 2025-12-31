package xyz.kpzip.mcio.blockentity.renderer.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;


@Environment(EnvType.CLIENT)
public class PerhipheralComponentModel extends Model<PerhipheralComponentModel.State> {
	
	public static final String BLOCK_BODY = "block_body";
	
	//private final ModelPart body;
	
	public PerhipheralComponentModel(ModelPart modelPart) {
		super(modelPart, RenderTypes::entitySolid);
		//this.body = modelPart.getChild(BLOCK_BODY);
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		meshDefinition.getRoot().addOrReplaceChild(
				BLOCK_BODY, CubeListBuilder.create().texOffs(0, 0).addBox(-8.0f, -8.0f, -8.0f, 16.0f, 16.0f, 16.0f), 
				PartPose.offset(0, 0, 0)
		);
		return LayerDefinition.create(meshDefinition, 16, 16);
	}

	
	@Environment(EnvType.CLIENT)
	public static class State {
		
	}

}
