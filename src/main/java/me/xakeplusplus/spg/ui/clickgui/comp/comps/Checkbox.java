package me.xakeplusplus.spg.ui.clickgui.comp.comps;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.BooleanSetting;
import me.xakeplusplus.spg.ui.clickgui.Button;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.gui.Gui;

import java.awt.Color;

public class Checkbox extends Comp {

    public Module feature;
    public Button parent;
    private int sOffset;

    public Checkbox(Button parent, Module module, Setting setting, int sOffset) {
        super(parent, module, setting, "checkbox");
        this.parent = parent;
        this.module = module;
        this.setting = setting;
        this.sOffset = sOffset;
    }

    public void onUpdate(int mouseX, int mouseY) {

    }

    public void drawScreen(int mouseX, int mouseY) {
        Gui.drawRect(parent.parent.x, parent.parent.y + parent.offset + sOffset, parent.parent.x + parent.parent.width, parent.parent.y + this.sOffset + parent.offset + parent.parent.barHeight, new Color(40, 40, 40, 200).getRGB());

        Gui.drawRect(parent.parent.x + parent.parent.width - 13, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 - 4, parent.parent.x + parent.parent.width - 5, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 + 4, ((BooleanSetting) setting).getValue() ? new Color(255, 36, 36).getRGB() : new Color(40, 40, 40, 200).getRGB());

        Gui.drawRect(parent.parent.x + parent.parent.width - 14, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 - 4, parent.parent.x + parent.parent.width - 13, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 + 4, new Color(0, 0, 0).getRGB());
        Gui.drawRect(parent.parent.x + parent.parent.width - 14, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 - 5, parent.parent.x + parent.parent.width - 5, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 - 4, new Color(0, 0, 0).getRGB());
        Gui.drawRect(parent.parent.x + parent.parent.width - 14, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 + 5, parent.parent.x + parent.parent.width - 5, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 + 4, new Color(0, 0, 0).getRGB());
        Gui.drawRect(parent.parent.x + parent.parent.width - 5, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 - 4, parent.parent.x + parent.parent.width - 6, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight / 2 + 4, new Color(0, 0, 0).getRGB());

        FontUtil.drawYCenteredString(setting.getName(), parent.parent.x + 1, parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - parent.parent.barHeight / 2, ((BooleanSetting) setting).getValue() ? -1 : new Color(127, 120, 120, 255).getRGB(), true);

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (inside(mouseX, mouseY, sOffset)) {
            ((BooleanSetting) setting).toggle();
        }
    }
}