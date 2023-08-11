package me.xakeplusplus.spg.setting.settings;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;

public class BooleanSetting extends Setting {

    private String name;
    private Module parent;
    private static boolean value, defaultValue;

    public BooleanSetting(Module parent, String name, boolean defaultValue)  {
        super(name, parent, "checkbox");
        this.parent = parent;
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public void toggle() {
        this.value = !value;
    }

    public boolean getValue() {
        return this.value;
    }
}