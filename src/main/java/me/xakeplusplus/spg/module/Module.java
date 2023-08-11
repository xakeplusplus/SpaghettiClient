package me.xakeplusplus.spg.module;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.module.modules.chat.ChatNotifications;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.SettingsManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;

public class Module {
	public static Minecraft mc = Minecraft.getMinecraft();
	
    public String name;
    private int key;
    private Category category;
    public boolean toggled;

    public Module(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
        this.key = 0;
        this.toggled = false;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (this.toggled) this.onEnable();
        else this.onDisable();
    }

    public void toggle() {
        this.toggled = !this.toggled;

        if (this.toggled) this.onEnable();
        else this.onDisable();
    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
        SpaghettiClient.log.info(this.getName() + " enabled");
        
        if (ChatNotifications.isOn() && mc.player != null && !this.getName().equalsIgnoreCase("clickgui")) {
        	mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + ChatFormatting.AQUA + this.getName() + " " + ChatFormatting.RESET + "has been" + ChatFormatting.GREEN + " enabled."));
        }
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        SpaghettiClient.log.info(this.getName() + " disabled");
        
        if (ChatNotifications.isOn() && mc.player != null && !this.getName().equalsIgnoreCase("clickgui")) {
        	mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + ChatFormatting.AQUA + this.getName() + " " + ChatFormatting.RESET + "has been" + ChatFormatting.RED + " disabled."));
        }
    }

    public void onUpdate() {}

    public void onRender() {}

    protected final Setting getSetting(String name) {
        for (Setting s : SpaghettiClient.instance.getSettingsManager().getSettingsByModule(this))
            if (s.getName().equalsIgnoreCase(name))
                return s;


    	return null;
    }

    public void addSettings(Setting setting) {
        ArrayList<Setting> settingArrayList = new ArrayList<>();
        settingArrayList.add(setting);
        this.addSettings(settingArrayList);
    }

    public void addSettings(ArrayList<Setting> settings) {
        SettingsManager.addSettings(settings);
    }

    public boolean hasSettings() {
        return SpaghettiClient.settingsManager.getSettingsByModule(this) != null;
    }

}
