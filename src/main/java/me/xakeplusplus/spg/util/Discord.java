package me.xakeplusplus.spg.util;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.xakeplusplus.spg.Reference;
import net.minecraft.client.Minecraft;

public class Discord {
    private static Minecraft mc = Minecraft.getMinecraft();

    public static String mState;

	private static final DiscordRichPresence discordRichPresence = new DiscordRichPresence();
    private static final DiscordRPC discordRPC = DiscordRPC.INSTANCE;
    static String discordID = "882321475284111450";
	
	public static void startrpc() {
		DiscordEventHandlers eventHandlers = new DiscordEventHandlers();

        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);
        
        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichPresence.largeImageKey = "th-3201710860";
        discordRichPresence.largeImageText = "https://github.com/xakeplusplus/SpaghettiClient";
        if(mc.player != null && mc.world != null) {

            if (!mc.isSingleplayer())
                mState = mc.getCurrentServerData().serverIP;
            else {
                mState = "In Singleplayer";
            }
        }
        else {
        	mState = "In the menu.";
        }
        discordRichPresence.state = mState;
        
        discordRichPresence.details = "SpaghettiClient v" + Reference.VERSION;
        discordRPC.Discord_UpdatePresence(discordRichPresence);
	}
	
	public static void stoprpc() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
