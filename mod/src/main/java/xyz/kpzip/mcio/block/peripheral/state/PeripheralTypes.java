package xyz.kpzip.mcio.block.peripheral.state;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Lifecycle;
import com.mojang.serialization.MapCodec;

import io.netty.buffer.ByteBuf;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import xyz.kpzip.mcio.MCIO;

public abstract class PeripheralTypes {
	
	public static final Registry<MapCodec<? extends PeripheralType>> PERIPHERAL_TYPES = new MappedRegistry<>(
			ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath(MCIO.MOD_ID, "peripheral_types")), Lifecycle.stable());
	
	public static final Codec<PeripheralType> CODEC = PeripheralTypes.PERIPHERAL_TYPES.byNameCodec().dispatch("peripheral_type", PeripheralType::getType, c -> c);
	public static final StreamCodec<ByteBuf, PeripheralType> STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC);
	
	
	public static final MapCodec<UnitState> UNIT_CODEC = register("unit_state_type", UnitState::values);
	public static final MapCodec<I2CBlockStates> I2C_CODEC = register("i2c_state_type", I2CBlockStates::values);
	
	
	public static <T extends Enum<T> & PeripheralType> MapCodec<T> register(String name, Supplier<T[]> values) {
		return Registry.register(PERIPHERAL_TYPES, Identifier.fromNamespaceAndPath(MCIO.MOD_ID, name), StringRepresentable.fromEnum(values).fieldOf(name));
	}
	
	public static void init() {
	}
	
	private PeripheralTypes() {}

}
