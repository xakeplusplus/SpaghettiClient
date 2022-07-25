package me.xakeplusplus.spg.module.modules.player;

import me.xakeplusplus.spg.events.CanCollideCheckEvent;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LiquidPlace extends Module {

    public LiquidPlace() {
        super("Liquid Place", Category.PLAYER);
    }

    @SubscribeEvent
    public void checkCanCollide(CanCollideCheckEvent event) {
        event.setCanceled(true);
    }
}
