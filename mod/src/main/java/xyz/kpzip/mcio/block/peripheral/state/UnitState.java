package xyz.kpzip.mcio.block.peripheral.state;

import com.mojang.serialization.MapCodec;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public enum UnitState implements StringRepresentable, PeripheralType {
	INSTANCE;

	@Override
	public MapCodec<? extends PeripheralType> getType() {
		return PeripheralTypes.UNIT_CODEC;
	}

	@Override
	public String getSerializedName() {
		return "unit";
	}
	
	@Override
	public Component getName() {
		return null;
	}

}
