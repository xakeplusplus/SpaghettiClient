package me.xakeplusplus.spg.module.modules.movement;

import org.lwjgl.input.Keyboard;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.events.PacketEvent;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class Velocity extends Module {
	
	public static Velocity instance = new Velocity();
	
	public Velocity() {
		super ("Velocity", Category.MOVEMENT);
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		SpaghettiClient.EVENT_BUS.register(this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
		SpaghettiClient.EVENT_BUS.unregister(this);
	}

	@SubscribeEvent
    public void receivePacket(PacketEvent.Receive event) {
		System.out.println("Packet Received");
		
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
