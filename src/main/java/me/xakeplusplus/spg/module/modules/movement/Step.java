package me.xakeplusplus.spg.module.modules.movement;

import me.xakeplusplus.spg.setting.settings.BooleanSetting;
import me.xakeplusplus.spg.setting.settings.NumberSetting;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.Minecraft;

public class Step extends Module {

	public NumberSetting stepHeight;
	public BooleanSetting whenSneaking;
	
	public Step() {
		super("Step", Category.MOVEMENT);

		stepHeight = new NumberSetting(this,"Step Height", 0.5f, 3f, 2f);
		whenSneaking = new BooleanSetting(this, "When Sneaking", true);

		this.addSettings(stepHeight);
		this.addSettings(whenSneaking);
	}
	
	@Override
	public void onUpdate() {
		
		if (((BooleanSetting) this.getSetting("When Sneaking")).getValue()) {
			
		} else {
			if (Minecraft.getMinecraft().player.isSneaking())
				Minecraft.getMinecraft().player.stepHeight = 0.5f;
			else {
				Minecraft.getMinecraft().player.stepHeight = (float) ((NumberSetting) getSetting("Step Height")).getValue();
			}
			
		}
		
	}

	@Override
	public void onDisable() {
		super.onDisable();
		Minecraft.getMinecraft().player.stepHeight = 0.5f;
	}
	
}
