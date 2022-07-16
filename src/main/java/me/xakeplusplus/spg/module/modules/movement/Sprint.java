package me.xakeplusplus.spg.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	private Minecraft mc = Minecraft.getMinecraft();
	
    public Sprint() {
    	super("Sprint", Category.MOVEMENT, Keyboard.KEY_M);
    }
    
    @Override
    public void onUpdate() {
    	if (mc.player.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally) {
    		mc.player.setSprinting(true);
    	}
    }
}
