package xyz.kpzip.mcio.block.peripheral.state;

import com.mojang.serialization.MapCodec;

import net.minecraft.util.StringRepresentable;

public enum I2CBlockStates implements StringRepresentable, PeripheralType {
	SINGLE("single"),
	CONTROLLER("controller"),
	SDA("sda"),
	SCL("scl");
	
	String name;
	
	private I2CBlockStates(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}

	@Override
	public MapCodec<I2CBlockStates> getType() {
		return PeripheralTypes.I2C_CODEC;
	}

}