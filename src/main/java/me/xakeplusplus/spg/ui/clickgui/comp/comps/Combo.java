package me.xakeplusplus.spg.ui.clickgui.comp.comps;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.OptionSetting;
import me.xakeplusplus.spg.ui.clickgui.Button;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.gui.Gui;

import java.awt.Color;

public class Combo extends Comp {

    public Module module;
    public Button parent;
    private int sOffset;

    public Combo(Button parent, Module module, Setting setting, int sOffset) {
        super(parent, module, setting, "combo");
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

        FontUtil.drawYCenteredString(((OptionSetting) setting).getValue(), parent.parent.x + parent.parent.width - 5 - FontUtil.getStringWidth(((OptionSetting) setting).getValue()), parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - parent.parent.barHeight / 2, new Color(127, 120, 120, 255).getRGB(), true);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && inside(mouseX, mouseY, sOffset)) {
            ((OptionSetting) setting).upIndex();
        } else if (mouseButton == 1 && inside(mouseX, mouseY, sOffset)) {
            ((OptionSetting) setting).downIndex();
        } else if (mouseButton == 2 && inside(mouseX, mouseY, sOffset)) {
            ((OptionSetting) setting).resetIndex();
        }
    }
}