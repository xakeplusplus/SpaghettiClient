package me.xakeplusplus.spg.setting;

import java.util.ArrayList;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Module;

public class SettingsManager {

	private static ArrayList<Setting> settings;

	public SettingsManager() {
		settings = new ArrayList<>();
	}

	public static ArrayList<Setting> getSettings() {
		return settings;
	}

	public static void addSettings(ArrayList<Setting> settings1) {
		settings.addAll(settings1);
	}

	public static Setting getSettingByName(String name) {
		for (Setting s : getSettings()) {
			if (name.equalsIgnoreCase(s.getName())) {
				return s;
			}
		}
		SpaghettiClient.log.info("Setting Not Found: " + name + " | returning null");
		return null;
	}

	public ArrayList<Setting> getSettingsByModule(Module feature) {
		ArrayList<Setting> settings1 = new ArrayList<>();

		for (Setting setting : getSettings()) {
			if (setting.getParentModule().equals(feature)) {
				settings1.add(setting);
			}
		}
		if (settings1.isEmpty())
			return null;
		return settings1;
	}
}