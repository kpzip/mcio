package xyz.kpzip.mcio.blockentity.renderer.model;

import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;

public class PerhipheralComponentModel extends Model<PerhipheralComponentModel.State> {
	
	
	
	public PerhipheralComponentModel(ModelPart modelPart) {
		super(modelPart, RenderType::entitySolid);
	}

	public class State {
		
	}

}
