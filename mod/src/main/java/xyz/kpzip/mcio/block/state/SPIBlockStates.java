package xyz.kpzip.mcio.block.state;

import net.minecraft.util.StringRepresentable;

public enum SPIBlockStates implements StringRepresentable {
	SINGLE("single"),
	CONTROLLER("controller"),
	SCK("sck"),
	MISO("miso"),
	MOSI("mosi"),
	CS("cs");
	
	
	String name;
	
	private SPIBlockStates(String name) {
		this.name = name;
	}

	@Override
	public String getSerializedName() {
		return this.name;
	}
	
	

}
