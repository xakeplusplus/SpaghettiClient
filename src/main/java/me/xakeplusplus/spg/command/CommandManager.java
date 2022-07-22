package me.xakeplusplus.spg.command;

import java.util.ArrayList;
import java.util.Arrays;

import com.mojang.realmsclient.gui.ChatFormatting;

import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.command.commands.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandManager {
	private static Minecraft mc = Minecraft.getMinecraft();
	private static String prefix = ".";

	public ArrayList<Command> commands;
	
	public CommandManager() {
		(commands = new ArrayList<Command>()).clear();
		MinecraftForge.EVENT_BUS.register(this);
		this.commands.add(new Prefix());
		this.commands.add(new Bind());
	}
	
	@SubscribeEvent
	public void onChat(ClientChatEvent event) {
		String msg = event.getMessage();
		
		if (!msg.startsWith(prefix))
			return;
		else {
			event.setCanceled(true);
			msg = msg.substring(prefix.length());
			
			if(msg.split(" ").length > 0) {
				boolean commandFound = false;
				String commandName = msg.split(" ")[0];
				if(commandName.equals("") || commandName.equals("help")) {
					sendCommandDescriptions();
				}else {
					for (Command c : commands) {
						if (c.name.equalsIgnoreCase(commandName)) {
							c.onCommand(Arrays.copyOfRange(msg.split(" "), 1, msg.split(" ").length), msg);
							commandFound = true;
							break;
						}
					}
					if (!commandFound) {
						mc.player.sendMessage(new TextComponentString(ChatFormatting.GOLD + "[" + Reference.NAME + "]" + ChatFormatting.RESET + " " + "Command was not found:" + ChatFormatting.RESET + " " + ChatFormatting.ITALIC + "" + ChatFormatting.RED + "" + ChatFormatting.BOLD + commandName));
					}
				}
			}
		}
	}
	
	public void sendCommandDescriptions() {
		for(Command c : SpaghettiClient.commandManager.commands) {
			mc.player.sendMessage(new TextComponentString("---------------\n" + ChatFormatting.BOLD + "" + ChatFormatting.DARK_GRAY +  c.name + ChatFormatting.RESET +  " - " + c.description + ChatFormatting.BOLD + "" + ChatFormatting.AQUA + " [\"" + c.syntax + "\"]" + "---------------\n"));
		}
	}
	
	public static void setCommandPrefix(String p) {
		prefix = p;
	}
}
