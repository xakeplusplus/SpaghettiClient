package me.xakeplusplus.spg.module.modules.render;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.setting.settings.NumberSetting;
import me.xakeplusplus.spg.setting.settings.OptionSetting;

import java.util.ArrayList;

public class FontModule extends Module {

    public OptionSetting fonts;
    public NumberSetting fontSize;

    public FontModule() {
        super("Font", Category.RENDER);

        ArrayList<String> fontList = new ArrayList<>();
        fontList.add("BebasNeueRegular");
        fontList.add("Dummy");
        fontList.add("StateDummy");

        fonts = new OptionSetting(this, "Font", fontList, "BebasNeueRegular");
        fontSize = new NumberSetting(this, "Font Size", 10, 20, 17);

        addSettings(fonts);
        addSettings(fontSize);
    }
}
