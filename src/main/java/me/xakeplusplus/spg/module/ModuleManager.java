package me.xakeplusplus.spg.module;

import me.xakeplusplus.spg.SpaghettiClient;
import me.xakeplusplus.spg.module.modules.movement.*;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {

    public ArrayList<Module> modules;

    public ModuleManager() {
        (modules = new ArrayList<Module>()).clear();
        this.modules.add(new Sprint());
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