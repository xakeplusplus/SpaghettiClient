package me.xakeplusplus.spg.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.Minecraft;

public class Fullbright extends Module {
	public Fullbright() {
		super("Fullbright", Category.RENDER);
		this.setToggled(true);
		this.setKey(Keyboard.KEY_P);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		Minecraft.getMinecraft().gameSettings.gammaSetting = 2000;
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
	}
}
