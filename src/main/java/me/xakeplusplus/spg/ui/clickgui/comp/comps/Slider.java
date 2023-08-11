package me.xakeplusplus.spg.ui.clickgui.comp.comps;

import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.NumberSetting;
import me.xakeplusplus.spg.ui.clickgui.Button;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class Slider extends Comp {

    public Module module;
    public Button parent;
    public NumberSetting setting;
    private int sOffset;
    private boolean sliding = false;


    public Slider(Button parent, Module module, Setting setting, int sOffset) {
        super(parent, module, setting, "slider");
        this.parent = parent;
        this.module = module;
        this.setting = (NumberSetting) setting;
        this.sOffset = sOffset;
    }

    public void onUpdate(int mouseX, int mouseY) {

    }

    public void drawScreen(int mouseX, int mouseY) {
        Gui.drawRect(parent.parent.x, parent.parent.y + parent.offset + sOffset, parent.parent.x + parent.parent.width, parent.parent.y + this.sOffset + parent.offset + parent.parent.barHeight + parent.parent.barHeight, new Color(40, 40, 40, 200).getRGB());
        FontUtil.drawYCenteredString(setting.getName(), parent.parent.x + 1, parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - (float) parent.parent.barHeight / 2 - 1, -1, true);

        float min = (float) (setting.getMin());
        float max = (float) (setting.getMax());
        float val = (float) (setting.getValue());
        float length = (float) Math.floor(val - min / max - min);

        // SLIDER
        Gui.drawRect(parent.parent.x + 1, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight - 2, parent.parent.x + parent.parent.width - 1, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight - 1, new Color(0, 0, 0).getRGB());
        Gui.drawRect(parent.parent.x + 1, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight - 2, parent.parent.x + (int)length * parent.parent.width, parent.parent.y + parent.offset + sOffset + parent.parent.barHeight - 1, new Color(255, 36, 36).getRGB());
        FontUtil.drawYCenteredString(setting.getValue().toString(), parent.parent.x + parent.parent.width - 3 - FontUtil.getStringWidth(setting.getValue().toString()), parent.parent.y + parent.offset + parent.parent.barHeight + sOffset - (float) parent.parent.barHeight / 2 - 1, -1, true);
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (inside(mouseX, mouseY, sOffset) && mouseButton == 1) {
            sliding = true;
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        sliding = false;
    }
}