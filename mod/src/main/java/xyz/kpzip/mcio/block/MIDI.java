package xyz.kpzip.mcio.block;

import org.jspecify.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import xyz.kpzip.mcio.communications.MIDICommunication;

public class MIDI extends NoteBlock {

	public MIDI(Properties properties) {
		super(properties);
	}
	
	@Override
	protected void playNote(@Nullable Entity entity, BlockState blockState, Level level, BlockPos blockPos) {
		if (((NoteBlockInstrument)blockState.getValue(INSTRUMENT)).worksAboveNoteBlock() || level.getBlockState(blockPos.above()).isAir()) {
			level.blockEvent(blockPos, this, 0, 0);
			level.gameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, blockPos);
			//level.scheduleTick(blockPos, this, 6);
			//MIDICommunication.noteOn(MIDICommunication.fromNoteBlockPitch(blockState.getValue(NoteBlock.NOTE), blockState.getValue(NoteBlock.INSTRUMENT)), 80, 0);
		}
	}
	
	@Override
	protected void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, @Nullable Orientation orientation, boolean bl) {
		boolean bl2 = level.hasNeighborSignal(blockPos);
		if (bl2 != (Boolean)blockState.getValue(POWERED)) {
			if (bl2) {
				this.playNote(null, blockState, level, blockPos);
			}

			level.setBlock(blockPos, blockState.setValue(POWERED, bl2), 3);
			if (bl2) {
				MIDICommunication.noteOn(MIDICommunication.fromNoteBlockPitch(blockState.getValue(NoteBlock.NOTE), blockState.getValue(NoteBlock.INSTRUMENT)), 80, 0);
			}
			else {
				MIDICommunication.noteOff(MIDICommunication.fromNoteBlockPitch(blockState.getValue(NoteBlock.NOTE), blockState.getValue(NoteBlock.INSTRUMENT)), 80, 0);
			}
		}
	}
	
	@Override
	protected void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
		MIDICommunication.noteOff(MIDICommunication.fromNoteBlockPitch(blockState.getValue(NoteBlock.NOTE), blockState.getValue(NoteBlock.INSTRUMENT)), 0, 0);
	}

}
