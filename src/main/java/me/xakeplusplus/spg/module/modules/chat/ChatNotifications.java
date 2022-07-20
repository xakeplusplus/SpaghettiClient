package me.xakeplusplus.spg.module.modules.chat;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;

public class ChatNotifications extends Module {
	private static boolean toggledBoolean;
	
	public ChatNotifications() {
		super("Chat Notifications", Category.CHAT);
	}
	
	@Override
	public void onUpdate() {
		toggledBoolean = this.toggled;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public static boolean isOn() {
		return toggledBoolean;
	}
}
