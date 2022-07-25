package me.xakeplusplus.spg.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.events.NetworkPacketEvent;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class NoFall extends Module {
	public NoFall() {
		super("NoFall", Category.PLAYER);
		this.setKey(Keyboard.KEY_NONE);
	}
	
	@SubscribeEvent
	public void receivePacket(NetworkPacketEvent event) {
		if (event.getPacket() instanceof CPacketPlayer) {
			final CPacketPlayer packet = (CPacketPlayer) event.getPacket();
			
			if (mc.player.fallDistance >= 3.0f)
				mc.player.onGround = true;
		}
	}
}
