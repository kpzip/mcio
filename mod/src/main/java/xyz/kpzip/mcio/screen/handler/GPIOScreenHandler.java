/**
 * 
 */
package xyz.kpzip.mcio.screen.handler;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import xyz.kpzip.mcio.screen.MCIOScreens;

/**
 * 
 * @author kpzip
 * 
 */
public class GPIOScreenHandler extends AbstractContainerMenu {

	/**
	 * 
	 */
	public GPIOScreenHandler(int syncId, Inventory playerinventory) {
		super(MCIOScreens.GPIO_SCREEN_HANDLER, syncId);
		
		int i, j;
		// player inventory
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerinventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Hotbar
        for (i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerinventory, i, 8 + i * 18, 142));
        }
	}

	@Override
	public ItemStack quickMoveStack(Player player, int i) {
		return ItemStack.EMPTY;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

}
