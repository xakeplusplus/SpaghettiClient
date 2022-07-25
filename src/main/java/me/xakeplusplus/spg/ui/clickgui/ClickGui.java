package me.xakeplusplus.spg.ui.clickgui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.ui.clickgui.comp.CheckBox;
import me.xakeplusplus.spg.ui.clickgui.comp.Combo;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.ui.clickgui.comp.Slider;
import me.xakeplusplus.spg.util.DrawingUtils;
import me.xakeplusplus.spg.util.RainbowColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

/*
 * TOOK THE CLICKGUI FROM EXEOS THEN IMPROVED IT MYSELF
 */

/*
 * I'll give you mini hint: check book gui and use the same method to draw a picture behind the text, a picture of your own choice which could also be a round box. then override the default button box
 */



public class ClickGui extends GuiScreen {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public int posX, posY, width, height, dragX, dragY;
	public boolean dragging;
    public Category selectedCategory;

    private Module selectedModule;
    public int modeIndex;

    public ArrayList<Comp> comps = new ArrayList<>();

    public ClickGui() {
        dragging = false;
        posX = getScaledRes().getScaledWidth() / 2; // -150
        posY = getScaledRes().getScaledHeight() / 2; // -100
        width = posX + 150 * 2;
        height = height + 200;
        selectedCategory = Category.COMBAT;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        if (dragging) {
            posX = mouseX - dragX;
            posY = mouseY - dragY;
        }
        width = posX + 300;
        height = posY + 200;
        Gui.drawRect(posX, posY - 10, width, posY, new Color(144,234,238).getRGB());
        //DrawingUtils.drawRoundedRect(posX, posY - 10, width, posY, new Color(144,234,238).getRGB());
        Gui.drawRect(posX, posY, width, height, new Color(40,40,40, 180).getRGB());
        //DrawingUtils.drawRoundedRect(posX, posY, width, height, new Color(40,40,40).getRGB());
        
        int offset = 0;
        for (Category category : Category.values()) {
            Gui.drawRect(posX,posY + 1 + offset,posX + 60,posY + 15 + offset,category.equals(selectedCategory) ? new Color(118, 150, 250).getRGB() : new Color(91, 91, 91).darker().getRGB());
            fontRenderer.drawString(category.name(),(int)posX + 2, (int)(posY + 5) + offset, new Color(255,210,117).getRGB());
            offset += 15;
        }
        offset = 0;
        for (Module m : SpaghettiClient.moduleManager.getModulesByCategory(selectedCategory)) {
            Gui.drawRect(posX + 65,posY + 1 + offset,posX + 155,posY + 15 + offset,m.isToggled() ? new Color(120,121,250).getRGB() : new Color(123, 149, 136, 180).darker().getRGB());
            fontRenderer.drawString(m.getName(),(int)posX + 67, (int)(posY + 5) + offset, new Color(255, 210, 117).getRGB());
            offset += 15;
        }

        for (Comp comp : comps) {
            comp.drawScreen(mouseX, mouseY);
        }

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (Comp comp : comps) {
            comp.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, posX, posY - 10, width, posY) && mouseButton == 0) {
            dragging = true;
            dragX = mouseX - posX;
            dragY = mouseY - posY;
        }
        int offset = 0;
        for (Category category : Category.values()) {
            if (isInside(mouseX, mouseY,posX,posY + 1 + offset,posX + 60,posY + 15 + offset) && mouseButton == 0) {
                selectedCategory = category;
                comps.clear();
            }
            offset += 15;
        }
        offset = 0;
        for (Module m : SpaghettiClient.moduleManager.getModulesByCategory(selectedCategory)) {
            if (isInside(mouseX, mouseY,posX + 65,posY + 1 + offset,posX + 125,posY + 15 + offset)) {
                if (mouseButton == 0) {
                    m.toggle();
                }
                if (mouseButton == 1) {
                    int sOffset = 3;
                    comps.clear();
                    if (SpaghettiClient.instance.getSettingsManager().getSettingsByMod(m) != null)
                    for (Setting setting : SpaghettiClient.instance.getSettingsManager().getSettingsByMod(m)) {
                        selectedModule = m;
                        if (setting.isCombo()) {
                            comps.add(new Combo(275, sOffset, this, selectedModule, setting));
                            sOffset += 15;
                        }
                        if (setting.isCheck()) {
                            comps.add(new CheckBox(275, sOffset, this, selectedModule, setting));
                            sOffset += 15;
                        }
                        if (setting.isSlider()) {
                            comps.add(new Slider(275, sOffset, this, selectedModule, setting));
                            sOffset += 25;
                        }
                    }
                }
            }
            offset += 15;
        }
        for (Comp comp : comps) {
            comp.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        dragging = false;
        for (Comp comp : comps) {
            comp.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        dragging = false;
    }

    public boolean isInside(int mouseX, int mouseY, double x, double y, double x2, double y2) {
        return (mouseX > x && mouseX < x2) && (mouseY > y && mouseY < y2);
    }

    public ScaledResolution getScaledRes() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }
}
