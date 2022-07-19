package me.xakeplusplus.spg.ui.clickgui.comp;

import java.awt.Color;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.ui.clickgui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class CheckBox extends Comp {
	public CheckBox(int x, int y, ClickGui parent, Module module, Setting setting) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.module = module;
        this.setting = setting;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        super.drawScreen(mouseX, mouseY);
        Gui.drawRect(parent.posX + x - 70, parent.posY + y, parent.posX + x + 10 - 70, parent.posY + y + 10,setting.getValBoolean() ? new Color(243,65,184).getRGB() : new Color(243, 65, 184, 60).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawString(setting.getName(), (int)(parent.posX + x - 55), (int)(parent.posY + y + 1), -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isInside(mouseX, mouseY, parent.posX + x - 70, parent.posY + y, parent.posX + x + 10 - 70, parent.posY + y + 10) && mouseButton == 0) {
            setting.setValBoolean(!setting.getValBoolean());
        }
    }

}
