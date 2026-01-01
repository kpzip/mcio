package xyz.kpzip.mcio.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import xyz.kpzip.mcio.item.component.MCIOComponents;

// Yet another mod that adds a wrench. At least this one has some snazzy textures.
public class Wrench extends Item {
	
	
	public Wrench(Properties properties) {
		super(properties);
	}
	
	@Override
	public InteractionResult use(Level level, Player player, InteractionHand interactionHand) {
		ItemStack stack = interactionHand == InteractionHand.MAIN_HAND ? player.getMainHandItem() : player.getOffhandItem();
		if (player.isShiftKeyDown()) {
			if (!level.isClientSide()) {
				stack.set(MCIOComponents.WRENCH_DATA, null);
				return InteractionResult.SUCCESS_SERVER;
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}

}
