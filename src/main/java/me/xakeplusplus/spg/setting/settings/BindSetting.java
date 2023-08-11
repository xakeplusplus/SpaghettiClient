package me.xakeplusplus.spg.setting.settings;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import org.lwjgl.input.Keyboard;

// idfk
public class BindSetting extends Setting {

    private static Module parent;
    private int key;

    public BindSetting(Module parent, int key) {
        super("Bind", parent, "bind");
        this.parent = parent;
        this.key = parent.getKey();
    }

    public boolean isEmpty() {
        return parent.getKey() < 0;
    }

    public String keyToString() {
        return isEmpty() ? "NONE" : Keyboard.getKeyName(parent.getKey());
    }

    public static BindSetting none(BindSetting setting) {
        return new BindSetting(parent, -1);
    }
}