package xyz.kpzip.mcio.block.peripheral.state;

import com.mojang.serialization.MapCodec;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public interface PeripheralType extends StringRepresentable {
	
	public default Component getName() {
		return Component.translatable("gui.mcio.perhipheral_selector." + this.getSerializedName());
	}
	
	public MapCodec<? extends PeripheralType> getType();

}
