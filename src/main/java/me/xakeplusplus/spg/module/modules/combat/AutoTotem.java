package me.xakeplusplus.spg.module.modules.combat;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class AutoTotem extends Module {
	private boolean isSwitching;
	private int lastSlot;

	public AutoTotem() {
		super("Auto Totem", Category.COMBAT);
		this.setKey(Keyboard.KEY_NONE);
	}
	
	@Override
	public void onUpdate() {
		
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {
			if (isSwitching) {
				swap(lastSlot, 2);
			}	
		}
		
		if (mc.player.getHeldItemOffhand().getItem() == Items.AIR) {
			swap(getItemSlot(), 0);
		}
	}
	
	public int getItemSlot() {
		if (Items.TOTEM_OF_UNDYING == mc.player.getHeldItemOffhand().getItem())
			return -1;
		for(int i = 36; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == Items.TOTEM_OF_UNDYING) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
        }
		return -1;
	}
	
	public void swap(int slot, int step) {
		if (slot == -1)
			return;
		if (step == 0) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
        }
        if (step == 1) {
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            isSwitching = true;
            lastSlot = slot;
        }
        if (step == 2) {
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            mc.playerController.windowClick(0, slot, 0, ClickType.PICKUP, mc.player);
            isSwitching = false;
        }

        mc.playerController.updateController();
	}
}
