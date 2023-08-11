package me.xakeplusplus.spg.ui.clickgui.comp;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.ui.clickgui.Button;
import net.minecraft.client.Minecraft;

public class Comp {
    public static Minecraft mc = Minecraft.getMinecraft();

    public int x, y, width, height;
    public Button parent;
    public Module module;
    public Setting setting;
    public String mode;

    public Comp(Button parent, Module module, Setting setting, String mode) {
        this.parent = parent;
        this.module = module;
        this.setting = setting;
        this.mode = mode;

        // custom font renderer

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public void drawScreen(int mouseX, int mouseY) {

    }

    public boolean inside(int mouseX, int mouseY, int sOffset) {
        if (mouseX >= parent.parent.x
                && mouseX <= parent.parent.x + parent.parent.width
                && mouseY >= parent.parent.y + parent.offset + sOffset
                && mouseY <= parent.parent.y + parent.parent.barHeight + parent.offset + sOffset) {
            return true;
        } else {
            return false;
        }
    }

    public void keyTyped(char typedChar, int keyCode) {

    }

    public void onUpdate(int mouseX, int mouseY) {

    }
}