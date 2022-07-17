package me.xakeplusplus.spg.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import io.netty.channel.ChannelHandlerContext;
import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.event.events.NetworkPacketEvent;
import me.xakeplusplus.spg.event.events.PacketEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

@Mixin(NetworkManager.class)
public class MixinNetworkManager {
	@Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
	private void onSendPacket(Packet<?> p_Packet, CallbackInfo ci) {
	    NetworkPacketEvent event = new NetworkPacketEvent(p_Packet);
	    SpaghettiClient.EVENT_BUS.post(event);

	    if (event.isCancelled()) {
	        ci.cancel();
	     }
	}

	@Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
	private void preSendPacket(Packet<?> packet, CallbackInfo ci) {
		PacketEvent.Send event = new PacketEvent.Send(packet);
		SpaghettiClient.EVENT_BUS.post(event);

		if (event.isCancelled()) {
			ci.cancel();
		}
	}

	@Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
	private void preChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
		PacketEvent.Receive event = new PacketEvent.Receive(packet);
		SpaghettiClient.EVENT_BUS.post(event);

		if (event.isCancelled()) {
			ci.cancel();
		}
	}

	@Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("TAIL"), cancellable = true)
	private void postSendPacket(Packet<?> packet, CallbackInfo ci) {
		PacketEvent.PostSend event = new PacketEvent.PostSend(packet);
		SpaghettiClient.EVENT_BUS.post(event);

		if (event.isCancelled()) {
			ci.cancel();
		}
	}

	@Inject(method = "channelRead0", at = @At("TAIL"), cancellable = true)
	private void postChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo ci) {
		PacketEvent.PostReceive event = new PacketEvent.PostReceive(packet);
		SpaghettiClient.EVENT_BUS.post(event);

		if (event.isCancelled()) {
			ci.cancel();
		}
	}
}
