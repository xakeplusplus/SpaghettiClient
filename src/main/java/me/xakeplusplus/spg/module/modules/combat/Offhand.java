package me.xakeplusplus.spg.module.modules.combat;

import java.util.ArrayList;

import me.xakeplusplus.spg.setting.settings.NumberSetting;
import me.xakeplusplus.spg.setting.settings.OptionSetting;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;

public class Offhand extends Module {
	private boolean isSwitching;
	private int lastSlot;
	public OptionSetting modeSetting;
	public NumberSetting healthSetting;

	public Offhand() {
		super("Offhand", Category.COMBAT);

		ArrayList<String> options = new ArrayList<String>();
		options.add("Crystal");
		options.add("GApple");

		modeSetting = new OptionSetting(this, "Mode", options, "Crystal");
		healthSetting = new NumberSetting(this, "Health", 36, 1, 15);

		this.addSettings(modeSetting);
		this.addSettings(healthSetting);
	}
	
	@Override
	public void onUpdate() {
		if (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory) {
			if (isSwitching) {
				swap(lastSlot, 2);
			}	
		}
		
		if (getHealthWithAbsorption() <= (float) ((NumberSetting) this.getSetting("Health")).getValue()) {
			swap(getTotemSlot(), -1);
		}
		
		else {
			if (((OptionSetting) this.getSetting("Mode")).getValue().equalsIgnoreCase("crystal")) {
				swap(getCrystalSlot(), -1);
			} else if (((OptionSetting) this.getSetting("Mode")).getValue().equalsIgnoreCase("gapple")) {
				swap(getGappleSlot(), -1);
			}
		}
	}
	
	public int getCrystalSlot() {
		if (Items.END_CRYSTAL == mc.player.getHeldItemOffhand().getItem())
			return -1;
		for(int i = 36; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == Items.END_CRYSTAL) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
        }
		return -1;
	}
	
	public int getGappleSlot() {
		if (Items.GOLDEN_APPLE == mc.player.getHeldItemOffhand().getItem())
			return -1;
		for(int i = 36; i >= 0; i--) {
            final Item item = mc.player.inventory.getStackInSlot(i).getItem();
            if(item == Items.GOLDEN_APPLE) {
                if (i < 9) {
                    return -1;
                }
                return i;
            }
        }
		return -1;
	}
	
	public int getTotemSlot() {
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
	
	public static float getHealthWithAbsorption() {
		return mc.player.getHealth() + mc.player.getAbsorptionAmount();
	}
}
