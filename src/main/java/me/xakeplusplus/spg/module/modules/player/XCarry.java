package me.xakeplusplus.spg.module.modules.player;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.events.PacketEvent;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class XCarry extends Module {

	public XCarry() {
		super("XCarry", Category.PLAYER);
		this.setKey(Keyboard.KEY_NONE);
	}
	
	@SubscribeEvent
	public void packetReceive(PacketEvent.Receive event) {
		if (event.getPacket() instanceof CPacketCloseWindow) {
			event.setCanceled(true);
		}
	}
}
