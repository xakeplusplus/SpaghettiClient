package me.xakeplusplus.spg.module.modules.render;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.Minecraft;

public class ClickGuiModule extends Module {
	public ClickGuiModule() {
		super("ClickGUI", Category.RENDER);
		this.setKey(Keyboard.KEY_RSHIFT);
	}
	
	@Override
	public void onEnable() {
		Minecraft.getMinecraft().displayGuiScreen(SpaghettiClient.instance.clickGui);
		setToggled(false);
	}
}
