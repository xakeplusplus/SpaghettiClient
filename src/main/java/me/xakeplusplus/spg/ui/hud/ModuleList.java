package me.xakeplusplus.spg.ui.hud;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.util.RainbowColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Collections;
import java.util.Comparator;

public class ModuleList extends Gui {
    private Minecraft mc = Minecraft.getMinecraft();

    public static ModuleList instance = new ModuleList();

    public static class ModuleComparator implements Comparator<Module> {

        @Override
        public int compare(Module arg0, Module arg1) {
            if (Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
                return -1;
            }
            if (Minecraft.getMinecraft().fontRenderer.getStringWidth(arg0.getName()) > Minecraft.getMinecraft().fontRenderer.getStringWidth(arg1.getName())) {
                return 1;
            }
            return 0;
        }
    }

    @SubscribeEvent
    public void renderGameOverlay(RenderGameOverlayEvent event) {
        Collections.sort(SpaghettiClient.moduleManager.modules, new ModuleComparator());
        ScaledResolution sr = new ScaledResolution(mc);

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            int y = 2;
            int[] counter = {1};
            for (Module m : SpaghettiClient.moduleManager.getModuleList()) {
                if (!m.getName().equalsIgnoreCase("") && m.isToggled()) {
                    mc.fontRenderer.drawStringWithShadow(m.getName(), sr.getScaledWidth() - mc.fontRenderer.getStringWidth(m.getName()) - 2, y, RainbowColor.rainbow(counter[0] * 300));
                    y += mc.fontRenderer.FONT_HEIGHT;
                    counter[0]++;
                }
            }
        }
    }
}
