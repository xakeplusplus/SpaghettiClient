package me.xakeplusplus.spg.util;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.SpaghettiClient;
import net.minecraft.client.Minecraft;

public class Discord {
	private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static final DiscordRPC discordRPC = DiscordRPC.INSTANCE;
    static String discordID = "882321475284111450";
	
	public static void startrpc() {
		DiscordEventHandlers eventHandlers = new DiscordEventHandlers();

        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        
        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.largeImageKey = "th-3201710860";
        discordRichPresence.largeImageText = "https://github.com/xakeplusplus/SpaghettiClient";
        if(Minecraft.getMinecraft().player != null && Minecraft.getMinecraft().world != null) {
        	discordRichPresence.state = Minecraft.getMinecraft().player.getName();
        }
        else {
        	discordRichPresence.state = "In the menu.";
        }
        
        discordRichPresence.details = "SpaghettiClient v" + Reference.VERSION;
        discordRPC.Discord_UpdatePresence(discordRichPresence);
        SpaghettiClient.log.info("Discord RPC Started");
	}
	
	public static void stoprpc() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
