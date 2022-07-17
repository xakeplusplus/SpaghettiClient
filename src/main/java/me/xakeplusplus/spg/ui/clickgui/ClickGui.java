package me.xakeplusplus.spg.ui.clickgui;

import java.awt.Color;
import java.io.IOException;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.util.RainbowColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class ClickGui extends GuiScreen {
	private Minecraft mc = Minecraft.getMinecraft();
	
	public int posX, posY, width, height, dragX, dragY;
	public boolean isDragging;
	
	public ClickGui() {
		isDragging = false;
		posX = getScaledResolution().getScaledWidth() / 2 - 100;
		posY = getScaledResolution().getScaledHeight() / 2 - 50;
		width = posX + 120;
		height = height + 100;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		if (isDragging) {
			posX = mouseX - dragX;
			posY = mouseY - dragY;
		}
		
		width = posX + 100;
		height = posY + 100;
		
		Gui.drawRect(posX, posY - 13, width, posY, new Color(144, 234, 238).getRGB());
		Gui.drawRect(posX, posY, width, height, new Color(40, 40, 40).getRGB()); // when toggled and shit 145, 161, 238
		
		int offset = 0;
		
		final int[] rainbowCounter = {1};
		for (Module m : SpaghettiClient.moduleManager.getModuleList()) {
			Gui.drawRect(posX, posY + 1 + offset, posX + 100, posY + 15 + offset, m.isToggled() ? new Color(120, 121, 250).getRGB() : new Color(40, 40, 40).getRGB()); //new Color(255, 210, 117, -1));
			mc.fontRenderer.drawString(m.getName(), posX + 2, (posY + 5) + offset, m.isToggled() ? new Color(255, 210, 117).getRGB() : new Color(255, 210, 117).getRGB());//new Color(255, 210, 117).getRGB());
			offset += 15;
			
			rainbowCounter[0]++;
		}
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		
		if (isInside(mouseX, mouseY, posX, posY - 13, width, posY) && mouseButton == 0) {
			isDragging = true;
			dragX = mouseX - posX;
			dragY = mouseY - posY;
		}
		int offset = 0;
		
		for (Module m : SpaghettiClient.moduleManager.getModuleList()) {
			if (isInside(mouseX, mouseY, posX, posY + 1 + offset, posX + 100, posY + 15 + offset) && mouseButton == 0) {
				m.toggle();
			}
			
			offset += 15;
		}
	}
	

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state) {
		super.mouseReleased(mouseX, mouseY, state);
		isDragging = false;
	}

	@Override
	public void initGui() {
		super.initGui();
		isDragging = false;
	}
	
	public boolean isInside(int mouseX, int mouseY, int x, int y, int width, int height) {
		return (mouseX > x && mouseX < width) && (mouseY > y && mouseY < height);
	}
	
	public ScaledResolution getScaledResolution() {
		return new ScaledResolution(mc);
	}
	
}
