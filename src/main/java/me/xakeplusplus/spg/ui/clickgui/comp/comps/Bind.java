package me.xakeplusplus.spg.ui.clickgui.comp.comps;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.BindSetting;
import me.xakeplusplus.spg.ui.clickgui.Button;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

import java.awt.Color;

public class Bind extends Comp {

    public Module feature;
    public Button parent;
    private int sOffset;
    private  boolean listening = false;

    public Bind(Button parent, Module feature, Setting setting, int sOffset) {
        super(parent, feature, setting, "bind");
        this.parent = parent;
        this.module = module;
        this.setting = setting;
        this.sOffset = sOffset;
    }

    public void onUpdate(int mouseX, int mouseY) {

    }

    public void drawScreen(int mouseX, int mouseY) {
        Gui.drawRect(parent.parent.x, parent.parent.y + parent.offset + sOffset, parent.parent.x + parent.parent.width, parent.parent.y + this.sOffset + parent.offset + parent.parent.barHeight, new Color(40, 40, 40, 200).getRGB());

        FontUtil.drawYCenteredString(setting.getName(), parent.parent.x + 1, parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - parent.parent.barHeight / 2, -1, true);

        if (listening) {
            FontUtil.drawYCenteredString("..", parent.parent.x + parent.parent.width - 5 - FontUtil.getStringWidth(".."), parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - parent.parent.barHeight / 2, new Color(127, 120, 120, 255).getRGB(), true);
        } else {
            FontUtil.drawYCenteredString(((BindSetting) setting).keyToString(), parent.parent.x + parent.parent.width - 5 - FontUtil.getStringWidth(((BindSetting) setting).keyToString()), parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - parent.parent.barHeight / 2, new Color(127, 120, 120, 255).getRGB(), true);
        }
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

        if (inside(mouseX, mouseY, sOffset)) {
            if (mouseButton == 0) {
                listening = !listening;
                return;
            }
            if (listening) {
                if (mouseButton == 1) {
                    if (!((BindSetting) setting).isEmpty()) {
                        BindSetting.none((BindSetting) setting);
                    }
                }
                listening = false;
            }
        }


    }

    public void keyTyped(char typedChar, int keyCode) {
        // TODO: Make it so that clickgui doesnt close when clicked ESC
        if (listening) {
            if (keyCode == Keyboard.KEY_ESCAPE || keyCode == Keyboard.KEY_NUMPADENTER) {
                listening = false;
            } else {
                module.setKey(keyCode);
                listening = false;
            }
        }
    }
}