package me.xakeplusplus.spg.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.command.Command;
import net.minecraft.util.text.TextComponentString;

public class Prefix extends Command {
	
	public Prefix() {
		super("Prefix", "Set the command prefix", "prefix NewPrefixHere");
	}

	@Override
	public void onCommand(String[] args, String command) {
		
		if (args.length == 1) {
			String newPrefix = args[0];
			SpaghettiClient.commandManager.setCommandPrefix(newPrefix);
			mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + ChatFormatting.AQUA + "Prefix" + ChatFormatting.RESET + " was set to" + ChatFormatting.ITALIC + " " + ChatFormatting.BOLD + "" + ChatFormatting.RED + newPrefix));
		}
	}
}
