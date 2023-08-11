package me.xakeplusplus.spg.module.modules.movement;

import java.util.ArrayList;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import net.minecraft.client.Minecraft;

public class Sprint extends Module {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static Sprint instance = new Sprint();
	
    public Sprint() {
    	super("Sprint", Category.MOVEMENT);
    	this.setKey(Keyboard.KEY_NONE);
    	
    	ArrayList<String> options = new ArrayList<String>();
    	options.add("Auto Sprint");
    	options.add("Normal");
    	
    	rSetting(new Setting("Mode", this, "Normal", options));
    	
    }
    
    @Override
    public void onUpdate() {
		if (mc.player.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally && this.getSetting("Mode").getValString().equalsIgnoreCase("normal")) {
    		mc.player.setSprinting(true);
    	} else if (this.getSetting("Mode").getValString().equalsIgnoreCase("auto sprint"))
    		mc.player.setSprinting(true);
    }
    
    public String getMode() {
    	return this.getSetting("Mode").getValString();
    }
    
}
