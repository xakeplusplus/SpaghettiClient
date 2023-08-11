package me.xakeplusplus.spg.setting.settings;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;

import java.util.ArrayList;

public class OptionSetting extends Setting {

    private Module parent;
    private String name;
    private ArrayList<String> options;
    private String defaultValue, value;
    private int index, defaultIndex;

    public OptionSetting(Module parent, String name, ArrayList<String> options, String defaultValue) {
        super(name, parent, "combo");
        this.parent = parent;
        this.name = name;
        this.options = options;
        this.defaultValue = defaultValue;
        this.value = defaultValue;

        int count = 0;
        for (String s : options) {
            if (s.equals(defaultValue)) {
                defaultIndex = count;
            }
            count++;
        }

        index = defaultIndex;

    }

    public void upIndex() {
        if (this.index >= options.size() - 1) {
            this.index = 0;
        } else {
            this.index++;
        }
        this.setValue(this.options.get(index));
    }

    public void downIndex() {
        if (this.index <= 0) {
            this.index = options.size() - 1;
        } else {
            this.index--;
        }
        this.setValue(this.options.get(index));
    }

    public void resetIndex() {
        this.index = defaultIndex;
        this.setValue(this.options.get(index));
    }

    public String getValue() {
        return this.options.get(index);
    }

    public void setValue(String val) {
        this.value = val;
    }
}