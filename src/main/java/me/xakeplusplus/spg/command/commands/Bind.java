package me.xakeplusplus.spg.command.commands;

import org.lwjgl.input.Keyboard;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.command.Command;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.util.text.TextComponentString;

public class Bind extends Command {
	
	public Bind() {
		super("Bind", "Bind module to a key", "bind ModuleNameHere KeyHere");
	}

	@Override
	public void onCommand(String[] args, String command) {
		
		if (args.length == 2) {
			String moduleName = args[0];
			String newKey = args[1];
			
			boolean moduleFound = false;
			
			for (Module m : SpaghettiClient.moduleManager.getModuleList()) {
				if (m.name.equalsIgnoreCase(moduleName)) {
					m.setKey(Keyboard.getKeyIndex(newKey.toUpperCase()));
					mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + ChatFormatting.AQUA + m.getName() + ChatFormatting.RESET + " was binded to" + ChatFormatting.ITALIC + " " + ChatFormatting.BOLD + "" + ChatFormatting.RED + newKey.toUpperCase()));
					moduleFound = true;
					break;
				}
			}
			
			if (!moduleFound)
				mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + "Module was not found:" + ChatFormatting.RESET + " " + ChatFormatting.ITALIC + "" + ChatFormatting.RED + "" + ChatFormatting.BOLD + moduleName));
		}
	}
}
