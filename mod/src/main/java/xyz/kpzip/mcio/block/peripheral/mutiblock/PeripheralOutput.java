package xyz.kpzip.mcio.block.peripheral.mutiblock;

import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import xyz.kpzip.mcio.blockentity.peripheral.component.PeripheralOutputComponentBlockEntity;

public class PeripheralOutput extends PeripheralMultiblockComponent {

	public PeripheralOutput(Block.Properties properties) {
		super(properties);
	}
	
	@Override
	public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
		return new PeripheralOutputComponentBlockEntity(blockPos, blockState);
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return simpleCodec(PeripheralOutput::new);
	}

}
