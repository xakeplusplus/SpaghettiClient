package me.xakeplusplus.spg.events;

import me.xakeplusplus.spg.SpaghettiClient;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.GuiScreenEvent.KeyboardInputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventProcessor {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public EventProcessor() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onKey(KeyboardInputEvent event) {
		SpaghettiClient.EVENT_BUS.post(event);
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event) {
		if (mc.player != null) {
			SpaghettiClient.moduleManager.onUpdate();
		}
	}
	
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent event) {
		SpaghettiClient.EVENT_BUS.post(event);
	}
	
	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event) {
		SpaghettiClient.EVENT_BUS.post(event);	
	}
	
	
}