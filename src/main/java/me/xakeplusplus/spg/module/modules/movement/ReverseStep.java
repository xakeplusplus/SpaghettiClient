package me.xakeplusplus.spg.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;

public class ReverseStep extends Module {
	
	public ReverseStep() {
		super("Reverse Step", Category.MOVEMENT);
		this.setKey(Keyboard.KEY_NONE);
		
		rSetting(new Setting("Height", this, 2, 0.0, 5, false));
		rSetting(new Setting("Speed", this, -10, -0.1, -20, false));
	}
	
	@Override
	public void onUpdate() {
		try {
            if (mc.player.isInLava() || mc.player.isInWater() || mc.player.isOnLadder())
            	return;
        } catch (Exception e) {
            return;
        }
		
		if (mc.player.onGround) {
			for (double y = 0.0; y < this.getSetting("Height").getValDouble(); y += 0.01) {
				if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = this.getSetting("Speed").getValDouble();
                    break;
                }
			}
		}
	}
}
