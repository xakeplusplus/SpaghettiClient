package me.xakeplusplus.spg.module.modules.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	private Minecraft mc = Minecraft.getMinecraft();
	
    public Sprint() {
    	super("Sprint", Category.MOVEMENT);
    	this.setKey(Keyboard.KEY_NONE);
    	
    }
    
    @Override
    public void onUpdate() {
		if (mc.player.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally) {
    		mc.player.setSprinting(true);
    	}
    }
}
