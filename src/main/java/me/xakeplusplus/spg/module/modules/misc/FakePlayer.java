package me.xakeplusplus.spg.module.modules.misc;

import com.mojang.authlib.GameProfile;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

public class FakePlayer extends Module {
        public static EntityOtherPlayerMP fakePlayer;
        private String uuid = "e4c0f223-ead4-4441-bfff-7f34d2e8bcab";

        public FakePlayer() {
                super("Fake Player", Category.MISC);
        }


        @Override
        public void onEnable() {
                super.onEnable();

                if (mc.player == null) {
                        setToggled(false);
                        return;
                }

                try {
                        fakePlayer = new EntityOtherPlayerMP(mc.world, new GameProfile(UUID.fromString(uuid), "aidn5"));
                        fakePlayer.copyLocationAndAnglesFrom(mc.player);
                        fakePlayer.prevRotationYawHead = mc.player.rotationYawHead;
                        mc.world.addEntityToWorld(-1000, fakePlayer);
                } catch (Exception e) {
                        e.printStackTrace();
                }

        }

        @Override
        public void onDisable() {
                super.onDisable();

                try {
                        mc.world.removeEntity(fakePlayer);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
