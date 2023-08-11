package me.xakeplusplus.spg.setting.settings;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;

public class NumberSetting<T> extends Setting<T> {

    private String name;
    private Module parent;
    private Number value, defaultValue;
    private Number min, max;

    public NumberSetting(Module parent, String name, int min, int max, int defaultValue) {
        super(name, parent, "slider");
        this.parent = parent;
        this.name = name;
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }
    public NumberSetting(Module parent, String name, double min, double max, double defaultValue) {
        super(name, parent, "slider");
        this.parent = parent;
        this.name = name;
        this.min = (float) min;
        this.max = (float) max;
        this.defaultValue = (float) defaultValue;
        this.value = (float) defaultValue;
    }
    public NumberSetting(Module parent, String name, float min, float max, float defaultValue) {
        super(name, parent, "slider");
        this.parent = parent;
        this.name = name;
        this.min = min;
        this.max = max;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public void setValue(Number val) {
        if (((Number)this.min).floatValue() > ((Number)val).floatValue()) {
            this.value = min;
        }
        else if (((Number)this.max).floatValue() < ((Number)val).floatValue()) {
            this.value = max;
        }
        else {
            this.value = (float) val;
        }
    }

    public Number getValue() {
        return value;
    }

    public Number getMin() {
        return min;
    }

    public Number getMax() {
        return max;
    }
}