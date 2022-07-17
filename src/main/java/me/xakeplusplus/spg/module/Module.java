package me.xakeplusplus.spg.module;

import com.lukflug.panelstudio.settings.Toggleable;

import me.xakeplusplus.spg.SpaghettiClient;
import net.minecraftforge.common.MinecraftForge;

public class Module {
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
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
        SpaghettiClient.log.info(this.getName() + " disabled");
    }

    public void onUpdate() {}

    public void onRender() {}

}
