package me.xakeplusplus.spg.ui.clickgui;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Panel {
    private Minecraft mc = Minecraft.getMinecraft();

    private String name;
    public int x, y, width, height, dragX, dragY, barHeight, offset;
    private boolean isDragging, isInside;
    private Category c;
    public ArrayList<Button> buttons;

    public Panel(int x, int y, int height, int width, int barHeight, Category c) {
        this.name = c.name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height; // 200
        this.barHeight = barHeight;
        this.c = c;
        buttons = new ArrayList<>();
    }

    public void onUpdate(int mouseX, int mouseY) {
        if (isDragging) {
            x = mouseX - dragX;
            y = mouseY - dragY;
        }

        for (Button b : buttons)
            b.onUpdate(mouseX, mouseY);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Gui.drawRect(x - 1, y - barHeight, x, y, new Color(41, 110, 251, 255).getRGB());
        Gui.drawRect(x + width, y - barHeight, x + width + 1, y, new Color(41, 110, 251, 255).getRGB());
        Gui.drawRect(x, y - barHeight - 1, x + width, y - barHeight, new Color(41, 110, 251, 255).getRGB());
        Gui.drawRect(x, y - barHeight, x + width, y, new Color(40, 40, 40, 230).getRGB());
//        Gui.drawRect(x, y, x + width, y + height, new Color(40, 40, 40).getRGB()); // bottom y + height


//        mc.fontRendererObj.drawString(this.name, x + 1, y  - 10, new Color(239, 158, 139).getRGB()); // 192, 96, 61
//        renderer.drawString(this.name, x + 1, y  - 11, new Color(239, 158, 139).getRGB(), false); // 192, 96, 61
//        FontUtil.drawString(this.name, (x + width / 2) - FontUtil.getStringWidth(this.name) / 2, (y - barHeight / 2) - FontUtil.getFontHeight() / 2, new Color(239, 158, 139).getRGB(), false); // 192, 96, 61
        FontUtil.drawTotalCenteredString(c.name, (x + width / 2), (y - barHeight / 2), new Color(239, 158, 139).getRGB(), false); // 192, 96, 61


        offset = 0;
        for (Button b : buttons) {
            b.onUpdate(mouseX, mouseY);

            if (b.equals(buttons.get(buttons.size() - 1))) {
                b.drawScreen(mouseX, mouseY, partialTicks, offset, barHeight, true);
            } else {
                b.drawScreen(mouseX, mouseY, partialTicks, offset, barHeight, false);
            }
            if (b.extended) {
                if (SpaghettiClient.settingsManager.getSettingsByModule(b.m) == null) {
                    offset += barHeight;
                } else {
                    for (Setting s : SpaghettiClient.settingsManager.getSettingsByModule(b.m)) {
                        offset += barHeight;
                    }
                    offset += barHeight;
                }
            }

            offset += barHeight;
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        for (Button b : buttons) {
            b.keyTyped(typedChar, keyCode);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0) {
            if (inside(mouseX, mouseY)) {
                isDragging = true;
                this.dragX = mouseX - x;
                this.dragY = mouseY - y;
            }
        }

        for (Button b : buttons) {
            b.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        isDragging = false;

        for (Button b : buttons) {
            b.mouseReleased(mouseX, mouseY, state);
        }
    }

    public boolean inside(int mouseX, int mouseY) {
        if (mouseX >= this.x
                && mouseX <= this.x + this.width
                && mouseY >= this.y - this.barHeight
                && mouseY <= this.y) {
            return true;
        } else {
            return false;
        }
    }
}