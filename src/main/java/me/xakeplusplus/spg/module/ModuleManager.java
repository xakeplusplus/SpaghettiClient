package me.xakeplusplus.spg.module;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.modules.chat.*;
import me.xakeplusplus.spg.module.modules.combat.*;
import me.xakeplusplus.spg.module.modules.exploits.*;
import me.xakeplusplus.spg.module.modules.misc.*;
import me.xakeplusplus.spg.module.modules.movement.*;
import me.xakeplusplus.spg.module.modules.render.*;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        (modules = new ArrayList<Module>()).clear();
        MinecraftForge.EVENT_BUS.register(this);
        this.modules.add(new Sprint());
        this.modules.add(new Velocity());
        this.modules.add(new Fullbright());
        this.modules.add(new DiscordRichPresence());
        this.modules.add(new ClickGuiModule());
        this.modules.add(new Step());
        this.modules.add(new ChatNotifications());
        this.modules.add(new ReverseStep());
        this.modules.add(new AutoTotem());
        this.modules.add(new Offhand());
        this.modules.add(new AutoRespawn());
        this.modules.add(new NoFall());
        this.modules.add(new XCarry());
        this.modules.add(new Hud());
        this.modules.add(new ChatSuffix());
        this.modules.add(new GreenText());
        this.modules.add(new GuiMove());
        this.modules.add(new LiquidPlace());
        this.modules.add(new PortalGodMode());
    }

    public void onUpdate() {
        modules.stream().filter(Module::isToggled).forEach(Module::onUpdate);
    }

    public void onRender() {
        modules.stream().filter(Module::isToggled).forEach(Module::onRender);
    }

    public Module getModule(String name) {
        for (Module i : this.modules) {
            if (i.getName().equalsIgnoreCase(name))
                return i;

        } return null;

    }

    public ArrayList<Module> getModuleList() {
        return this.modules;
    }

    public static List<Module> getModulesByCategory(Category c) {
        List<Module> modules = new ArrayList<Module>();

        for (Module i : SpaghettiClient.moduleManager.modules) {
            if (i.getCategory().equals(c)) {
                modules.add(i);
            }
        } return modules;
    }
}
