package me.xakeplusplus.spg.module.modules.movement;

import me.xakeplusplus.spg.setting.settings.NumberSetting;
import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;

import java.util.ArrayList;

public class ReverseStep extends Module {

	public NumberSetting heightSetting;
	public NumberSetting speedSetting;

	public ReverseStep() {
		super("Reverse Step", Category.MOVEMENT);

		heightSetting = new NumberSetting(this, "Height", 2f, 0.0f, 5f);
		speedSetting = new NumberSetting(this, "Speed", -20f, -0.1f, -10f);

		addSettings(heightSetting);
		addSettings(speedSetting);
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
			for (float y = 0.0f; y < (float) ((NumberSetting) this.getSetting("Height")).getValue(); y += 0.01f) {
				if (!mc.world.getCollisionBoxes(mc.player, mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    mc.player.motionY = (float) ((NumberSetting) this.getSetting("Height")).getValue();
                    break;
                }
			}
		}
	}
}
