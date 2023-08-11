package me.xakeplusplus.spg.util;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.font.GlyphPage;
import me.xakeplusplus.spg.font.GlyphPageFontRenderer;
import me.xakeplusplus.spg.setting.Setting;
import me.xakeplusplus.spg.setting.settings.NumberSetting;

import java.awt.*;

public class FontUtil {

    public static GlyphPageFontRenderer renderer;

    public FontUtil() {
        char[] chars = new char[256];

        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) i;
        }

        // DEFAULT FONT SIZE: 17
        int fontSize = 0;
        for (Setting s : SpaghettiClient.settingsManager.getSettingsByModule(SpaghettiClient.moduleManager.getModule("Font"))) {
            if (s.getName().equalsIgnoreCase("font size")) {
                fontSize  = (int) ((NumberSetting) s).getValue();
            }
        }

        GlyphPage glyphPage = new GlyphPage(new Font("BebasNeueRegular", Font.PLAIN, fontSize), true, true);

        glyphPage.generateGlyphPage(chars);
        glyphPage.setupTexture();
        renderer = new GlyphPageFontRenderer(glyphPage, glyphPage, glyphPage, glyphPage);
    }

    public static void drawString(String text, float x, float y, int color, boolean dropShadow) {
        renderer.drawString(text, x, y, color, dropShadow);
    }

    public static void drawXCenteredString(String text, float x, float y, int color, boolean dropShadow) {
        renderer.drawString(text, x - getStringWidth(text) / 2, y, color, dropShadow);
    }

    public static void drawYCenteredString(String text, float x, float y, int color, boolean dropShadow) {
        renderer.drawString(text, x, y - getFontHeight() / 2, color, dropShadow);
    }


    public static void drawTotalCenteredString(String text, float x, float y, int color, boolean dropShadow) {
        renderer.drawString(text, x - getStringWidth(text) / 2, y - getFontHeight() / 2, color, dropShadow);
    }

    public static String trimStringToWidth(String text, int width) {
        return renderer.trimStringToWidth(text, width);
    }

    public static int getStringWidth(String text) {
        return renderer.getStringWidth(text);
    }

    public static int getFontHeight() {
        return renderer.getFontHeight();
    }
}