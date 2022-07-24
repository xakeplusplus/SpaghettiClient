package me.xakeplusplus.spg.module.modules.render;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.ui.hud.ModuleList;
import net.minecraftforge.common.MinecraftForge;

public class Hud extends Module {

    public Hud() {
        super("HUD", Category.RENDER);

        rSetting(new Setting("ModuleList", this, false));
    }


    @Override
    public void onEnable() {
        super.onEnable();
        MinecraftForge.EVENT_BUS.register(ModuleList.instance);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        MinecraftForge.EVENT_BUS.unregister(ModuleList.instance);
    }
}
