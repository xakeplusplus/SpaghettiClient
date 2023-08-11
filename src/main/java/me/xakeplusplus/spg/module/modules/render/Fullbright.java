package me.xakeplusplus.spg.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.Minecraft;

public class Fullbright extends Module {
	public Fullbright() {
		super("Fullbright", Category.RENDER);
		this.setKey(Keyboard.KEY_P);
	}
	
	public void onEnable() {
		Minecraft.getMinecraft().gameSettings.gammaSetting = 2000;
	}
	
	public void onDisable() {
		Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
	}
}
