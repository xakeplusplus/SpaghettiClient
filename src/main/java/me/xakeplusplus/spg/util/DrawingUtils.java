package me.xakeplusplus.spg.util;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class DrawingUtils {
	
	public static void drawRoundedRect(int x, int y, int xSize, int ySize, int color) {
		int width = x + xSize;
        int height = y + ySize;
        
        // Top rounding
        Gui.drawRect(x + 1, y, width - 1, height, color);

        // Middle rect
        Gui.drawRect(x, y + 1, width, height - 1, color);
	}
	
	public static void drawRoundedRectb(int x, int y, int width, int height, int cornerRadius, Color color) {
	     Gui.drawRect(x, y + cornerRadius, x + cornerRadius, y + height - cornerRadius, color.getRGB());
	     Gui.drawRect(x + cornerRadius, y, x + width - cornerRadius, y + height, color.getRGB());
	     Gui.drawRect(x + width - cornerRadius, y + cornerRadius, x + width, y + height - cornerRadius, color.getRGB());
   }
}
