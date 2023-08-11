package me.xakeplusplus.spg.ui.clickgui;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.module.ModuleManager;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.util.ArrayList;


public class ClickGui extends GuiScreen {

    public ArrayList<Panel> panels;
    public static boolean inGUI = false;

    public ClickGui() {
        panels = new ArrayList<>();

        int xOffset = 0;
        for (Category c : Category.values()) {
            int moduleCount = ModuleManager.getModulesByCategory(c).size();
            Panel panel;

            panel = new Panel(10 + xOffset, 20, moduleCount * 16, 125, 14, c);

            for (Module m : ModuleManager.getModulesByCategory(c)) {
                Button button = new Button(panel, m);
                panel.buttons.add(button);
            }
            panels.add(panel);
            xOffset += 130;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (Panel p : panels) {
            p.onUpdate(mouseX, mouseY);
            p.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);

        for (Panel p : panels) {
            p.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        for (Panel p : panels) {
            p.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);

        for (Panel p : panels) {
            p.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void initGui() {
        MinecraftForge.EVENT_BUS.register(this);
        mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        inGUI = true;

    }

    @Override
    public void onGuiClosed() {
        MinecraftForge.EVENT_BUS.unregister(this);
        mc.entityRenderer.loadEntityShader(null);
        inGUI = false;
        super.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return super.doesGuiPauseGame();
    }

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (inGUI && event.getType() == RenderGameOverlayEvent.ElementType.CROSSHAIRS) {
            event.setCanceled(true);
        }
    }
}