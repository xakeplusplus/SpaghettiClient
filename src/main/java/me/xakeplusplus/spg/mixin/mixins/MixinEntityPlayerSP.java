package me.xakeplusplus.spg.mixin.mixins;

import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.events.PlayerUpdateEvent;
import me.xakeplusplus.spg.module.modules.movement.Sprint;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

@Mixin(EntityPlayerSP.class)
public abstract class MixinEntityPlayerSP extends EntityPlayer {

	/*public MixinEntityPlayerSP(World worldIn, GameProfile gameProfileIn) {
		super(worldIn, gameProfileIn);
	}*/

	public MixinEntityPlayerSP() {
		super(null, null);
	}
	
	@Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
	public void onUpdate(CallbackInfo ci) {
		PlayerUpdateEvent event = new PlayerUpdateEvent();
		SpaghettiClient.EVENT_BUS.post(event);
		if (event.isCanceled()) {
			ci.cancel();
		}
	}
	
	@Redirect(method = {"onLivingUpdate"}, at = @At(value="INVOKE", target="Lnet/minecraft/client/entity/EntityPlayerSP;setSprinting(Z)V", ordinal=2))
	public void onLivingUpdate(EntityPlayerSP entityPlayerSP, boolean sprinting) {
		Sprint sprint = (Sprint) SpaghettiClient.moduleManager.getModule("Sprint");
		
		if (sprint.isToggled() && sprint.getMode().equalsIgnoreCase("Auto Sprint") && (Minecraft.getMinecraft().player.movementInput.moveForward != 0.0f || Minecraft.getMinecraft().player.movementInput.moveStrafe != 0.0f))
			entityPlayerSP.setSprinting(true);
		else
			entityPlayerSP.setSprinting(sprinting);
	}
}
