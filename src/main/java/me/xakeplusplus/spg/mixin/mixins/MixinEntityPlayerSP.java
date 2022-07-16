package me.xakeplusplus.spg.mixin.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.event.events.PlayerUpdateEvent;
import me.xakeplusplus.spg.module.modules.movement.Sprint;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP extends AbstractClientPlayer {
	public MixinEntityPlayerSP() {
		super(null, null);
	}
	
	@Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
	public void onUpdate(CallbackInfo ci) {
		PlayerUpdateEvent event = new PlayerUpdateEvent();
		SpaghettiClient.EVENT_BUS.post(event);
		if (event.isCancelled()) {
			ci.cancel();
		}
	}
}
