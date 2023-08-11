package me.xakeplusplus.spg.ui.clickgui.comp;

import java.awt.Color;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.ui.clickgui.ClickGui;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Combo extends Comp {
	 public Combo(int x, int y, ClickGui parent, Module module, Setting setting) {
	        this.x = x;
	        this.y = y;
	        this.parent = parent;
	        this.module = module;
	        this.setting = setting;
	    }


	    @Override
	    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
	        super.mouseClicked(mouseX, mouseY, mouseButton);
	        if (isInside(mouseX, mouseY, parent.posX + x - 70, parent.posY + y, parent.posX + x, parent.posY + y + 10) && mouseButton == 0) {
	            int max = setting.getOptions().size();
	            if (parent.modeIndex + 1 >= max) {
	                parent.modeIndex = 0;
	            } else {
	                parent.modeIndex++;
	            }
	            setting.setValString(setting.getOptions().get(parent.modeIndex));
	        }
	    }

	    @Override
	    public void drawScreen(int mouseX, int mouseY) {
	        super.drawScreen(mouseX, mouseY);
	        Gui.drawRect(parent.posX + x - 93, parent.posY + y, parent.posX + x + Minecraft.getMinecraft().fontRenderer.getStringWidth(setting.getValString()) - 55, parent.posY + y + 10,setting.getValBoolean() ? new Color(119, 119, 119).darker().getRGB() : new Color(119, 119, 119).darker().getRGB());
	        Minecraft.getMinecraft().fontRenderer.drawString(setting.getName() + ": " + setting.getValString(), (int)(parent.posX + x - 89), (int)(parent.posY + y + 1), new Color(243,65,184).brighter().getRGB());
	    }
}
