package me.xakeplusplus.spg.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import net.minecraft.client.Minecraft;

public class Step extends Module {
	
	public Step() {
		super("Step", Category.MOVEMENT);
		this.setKey(Keyboard.KEY_NONE);
		
		rSetting(new Setting("Step Height", this, 2, 0.5, 2.5, false));
	}
	
	@Override
	public void onUpdate() {
		Minecraft.getMinecraft().player.stepHeight = (float) getSetting("Step Height").getValDouble();
	}

	@Override
	public void onDisable() {
		super.onDisable();
		Minecraft.getMinecraft().player.stepHeight = 0.5f;
	}
	
}
