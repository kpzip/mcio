package xyz.kpzip.mcio.block.perhipheral.state;

import net.minecraft.util.StringRepresentable;

public enum I2CBlockStates  implements StringRepresentable {
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

}