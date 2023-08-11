package me.xakeplusplus.spg.ui.clickgui;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.BindSetting;
import me.xakeplusplus.spg.ui.clickgui.comp.Comp;
import me.xakeplusplus.spg.ui.clickgui.comp.comps.Bind;
import me.xakeplusplus.spg.ui.clickgui.comp.comps.Checkbox;
import me.xakeplusplus.spg.ui.clickgui.comp.comps.Combo;
import me.xakeplusplus.spg.ui.clickgui.comp.comps.Slider;
import me.xakeplusplus.spg.util.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

import java.awt.Color;
import java.util.ArrayList;

public class Button {
    private Minecraft mc = Minecraft.getMinecraft();

    public Panel parent;
    public Module m;
    public int offset;
    private int sOffset;
    public boolean extended;
    public ArrayList<Comp> comps;
    public boolean last;

    public Button(Panel parent, Module m) {
        this.parent = parent;
        this.m = m;
        comps = new ArrayList<>();
    }

    public void onUpdate(int mouseX, int mouseY) {
        for (Comp comp : comps) {
            comp.onUpdate(mouseX, mouseY);
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks, int offset,int boxHeight, boolean last) {
        this.offset = offset;
        this.last = last;

        Gui.drawRect(parent.x - 1, parent.y + offset, parent.x, parent.y + offset + parent.barHeight, new Color(41, 110, 251).getRGB());
        Gui.drawRect(parent.x + parent.width, parent.y + offset, parent.x + parent.width + 1, parent.y + offset + parent.barHeight, new Color(41, 110, 251).getRGB());

        if (last) {
            if (extended) {
                Gui.drawRect(parent.x, parent.y + offset + parent.barHeight + sOffset, parent.x + parent.width + 1, parent.y + offset + parent.barHeight + sOffset, new Color(41, 110, 251).getRGB());
            } else {
                Gui.drawRect(parent.x, parent.y + offset +  parent.barHeight, parent.x + parent.width + 1, parent.y + offset + parent.barHeight + 1, new Color(41, 110, 251).getRGB());
            }
        }

        Gui.drawRect(parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.barHeight, m.isToggled() ? new Color(239, 158, 139, 255).getRGB() : new Color(40, 40, 40, 200).getRGB());

        FontUtil.drawTotalCenteredString(m.getName(),  parent.x + parent.width / 2, parent.y + offset + boxHeight - parent.barHeight / 2, -1, true); // 192, 96, 61

        for (Comp comp : comps) {
            comp.drawScreen(mouseX, mouseY);
        }
    }

    protected void keyTyped(char typedChar, int keyCode) {
        for (Comp comp : comps) {
            comp.keyTyped(typedChar, keyCode);
        }
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (mouseButton == 0 && inside(mouseX, mouseY)) {
            m.toggle();
        }
        else if (mouseButton == 1 && inside(mouseX, mouseY)) {
            extended = !extended;

            comps.clear();

            if (extended) {
                sOffset = parent.barHeight;
                if (SpaghettiClient.settingsManager.getSettingsByModule(this.m) != null) {
                    for (Setting s: SpaghettiClient.settingsManager.getSettingsByModule(this.m)) {
                        if (s.getMode().equalsIgnoreCase("checkbox")) {
                            comps.add(new Checkbox(this, this.m, s, sOffset));
                        } else if (s.getMode().equalsIgnoreCase("combo")) {
                            comps.add(new Combo(this, this.m, s, sOffset));
                        } else if (s.getMode().equalsIgnoreCase("slider")) {
                            comps.add(new Slider(this, this.m, s, sOffset));
                        }
                        sOffset += parent.barHeight;
                    }
                }
                comps.add(new Bind(this, this.m, new BindSetting(m, m.getKey()), sOffset));
                sOffset = parent.barHeight;
            }
        }

        for (Comp comp : comps) {
            comp.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (Comp comp : comps) {
            comp.mouseReleased(mouseX, mouseY, state);
        }
    }

    public boolean inside(int mouseX, int mouseY) {
        if (mouseX >= parent.x
                && mouseX <= parent.x + parent.width
                && mouseY >= parent.y + offset
                && mouseY <= parent.y + parent.barHeight + offset) {
            return true;
        } else {
            return false;
        }
    }
}