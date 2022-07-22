package me.xakeplusplus.spg.module.modules.misc;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;

public class AutoRespawn extends Module {
	
	public AutoRespawn() {
		super("Auto Respawn", Category.MISC);
		this.setKey(Keyboard.KEY_NONE);
	}
	
	@Override
	public void onUpdate() {
		if (mc.player.isDead)
			mc.player.respawnPlayer();
	}
}
