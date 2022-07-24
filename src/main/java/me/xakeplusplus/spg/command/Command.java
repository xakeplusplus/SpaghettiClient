package me.xakeplusplus.spg.command;

import net.minecraft.client.Minecraft;

public abstract class Command {
	public static Minecraft mc = Minecraft.getMinecraft();
	public String name, description, syntax;
	
	public Command(String name, String description, String syntax) {
		super();
		this.name = name;
		this.syntax = syntax;
	}

	public abstract void onCommand(String[] args, String command);
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}
	
	
}
