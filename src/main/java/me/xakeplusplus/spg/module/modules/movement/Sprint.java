package me.xakeplusplus.spg.module.modules.movement;

import java.util.ArrayList;

import me.xakeplusplus.spg.setting.settings.OptionSetting;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;

public class Sprint extends Module {

	public OptionSetting modeSetting;
	ArrayList<String> options;
	
    public Sprint() {
    	super("Sprint", Category.MOVEMENT);

    	options = new ArrayList<>();
    	options.add("Auto Sprint");
    	options.add("Normal");
		modeSetting = new OptionSetting(this, "Mode", options, "Normal");
    	addSettings(modeSetting);
    }
    
    @Override
    public void onUpdate() {
		if (mc.player.moveForward > 0 && !mc.player.isSneaking() && !mc.player.collidedHorizontally && ((OptionSetting) this.getSetting("Mode")).getValue().equalsIgnoreCase("normal")) {
    		mc.player.setSprinting(true);
    	} else if (((OptionSetting) this.getSetting("Mode")).getValue().equalsIgnoreCase("auto sprint"))
    		mc.player.setSprinting(true);
    }
    
    public String getMode() {
    	return ((OptionSetting) this.getSetting("Mode")).getValue();
    }
    
}
