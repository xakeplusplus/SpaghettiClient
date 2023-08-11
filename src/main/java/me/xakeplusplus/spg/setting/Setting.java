package me.xakeplusplus.spg.setting;

import me.xakeplusplus.spg.module.Module;

public class Setting<T> {
	Module m;
	String name;
	String mode;

	public Setting(String name, Module m, String mode) {
		this.name = name;
		this.m = m;
		this.mode = mode;
	}

	public String getName() {
		return this.name;
	}

	public Module getParentModule() {
		return this.m;
	}

	public String getMode() {
		return this.mode;
	}
}