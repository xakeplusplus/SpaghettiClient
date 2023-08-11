package me.xakeplusplus.spg.module.modules.chat;

import me.xakeplusplus.spg.Reference;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Arrays;

public class GreenText extends Module {

    public GreenText() {
        super("Green Text", Category.CHAT);
    }

    @SubscribeEvent
    public void onChat(ClientChatEvent event) {
        for (String s : Arrays.asList("/", ".", "-", ",", ":", ";", "'", "\"", "+", "\\", "@", "#")) {
            if (event.getMessage().startsWith(s))
                return;
        }
        event.setMessage(" > " + event.getMessage());
    }
}
