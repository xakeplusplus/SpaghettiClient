package me.xakeplusplus.spg.module.modules.misc;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.util.Discord;

public class DiscordRichPresence extends Module {
	public DiscordRichPresence() {
		super("DiscordRPC", Category.MISC);
		this.setKey(Keyboard.KEY_U);
	}

	public void onEnable() {
		Discord.startrpc();
	}
	
	@Override
	public void onDisable() {
		Discord.stoprpc();
	}
}
