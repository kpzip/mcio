package xyz.kpzip.mcio.block.perhipheral.mutiblock;

import com.mojang.serialization.MapCodec;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;

public class PerhipheralInput extends PerhipheralMultiblockComponent {

	public PerhipheralInput(Block.Properties properties) {
		super(properties);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(PerhipheralInput::new);
	}

}
