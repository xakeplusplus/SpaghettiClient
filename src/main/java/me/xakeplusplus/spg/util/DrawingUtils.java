package me.xakeplusplus.spg.util;

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
}
