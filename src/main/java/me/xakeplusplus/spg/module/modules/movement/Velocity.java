package me.xakeplusplus.spg.module.modules.movement;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.events.PacketEvent;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity extends Module {
	
	public static Velocity instance = new Velocity();
	
	public Velocity() {
		super("Velocity", Category.MOVEMENT);
	}
	
	@SubscribeEvent
    public void receivePacket(PacketEvent.Receive event) {
        if (event.getPacket() instanceof SPacketEntityVelocity) {
            if (((SPacketEntityVelocity) event.getPacket()).getEntityID() == mc.player.getEntityId()) {
                event.setCanceled(true);
            }
        }

        if (event.getPacket() instanceof SPacketExplosion) {
            event.setCanceled(true);
        }
    }
}
