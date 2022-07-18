package me.xakeplusplus.spg;

import me.xakeplusplus.spg.event.EventProcessor;
import me.xakeplusplus.spg.module.Module;
import me.xakeplusplus.spg.module.ModuleManager;
import me.xakeplusplus.spg.setting.SettingsManager;
import me.xakeplusplus.spg.ui.clickgui.ClickGui;
import me.zero.alpine.EventManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class SpaghettiClient {
    private Minecraft mc = Minecraft.getMinecraft();
    
    public static final EventManager EVENT_BUS = new EventManager();
    public SettingsManager settingsManager;
    public static ModuleManager moduleManager;
    public static EventProcessor eventProcessor;
    public static ClickGui clickGui;
    public static final Logger log = LogManager.getLogger("spaghetti-client");
    
    @Mod.Instance
    public static SpaghettiClient instance = new SpaghettiClient();


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(instance);
        settingsManager = new SettingsManager();
        log.info("Settings Manager Initialized");
        moduleManager = new ModuleManager();
        log.info("Module Manager Initialized");
        eventProcessor = new EventProcessor();
        log.info("Event Processor Initialized.");
        clickGui = new ClickGui();
        log.info("ClickGui Initialized");
    }

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent event) {
        if (mc.world == null || mc.player == null)
            return;

        try {
            if (Keyboard.isCreated()) {
                if (Keyboard.getEventKeyState()) {
                    int keyCode = Keyboard.getEventKey();
                    if (keyCode <= 0)
                        return;
                    for (Module i : moduleManager.modules) {
                        if (i.getKey() == keyCode && keyCode > 0)
                            i.toggle();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public SettingsManager getSettingsManager() {
    	return settingsManager;
    }
}
