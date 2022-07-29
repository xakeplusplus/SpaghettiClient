package me.xakeplusplus.spg.module.modules.player;

import me.xakeplusplus.spg.module.Category;
import me.xakeplusplus.spg.module.Module;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

public class GuiMove extends Module {

    public GuiMove() {
        super("Gui Move", Category.PLAYER);
    }

    @SubscribeEvent
    public void onInput(InputUpdateEvent event) {
        if (mc.player == null || mc.world == null) return;

        if (mc.currentScreen instanceof GuiChat) return;

        mc.player.movementInput.moveStrafe = 0.0F;
        mc.player.movementInput.moveForward = 0.0F;

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindForward.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()));
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindForward.getKeyCode()))
        {
            ++mc.player.movementInput.moveForward;
            mc.player.movementInput.forwardKeyDown = true;
        }
        else
        {
            mc.player.movementInput.forwardKeyDown = false;
        }

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindBack.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()));
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindBack.getKeyCode()))
        {
            --mc.player.movementInput.moveForward;
            mc.player.movementInput.backKeyDown = true;
        }
        else
        {
            mc.player.movementInput.backKeyDown = false;
        }

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindLeft.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()));
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindLeft.getKeyCode()))
        {
            ++mc.player.movementInput.moveStrafe;
            mc.player.movementInput.leftKeyDown = true;
        }
        else
        {
            mc.player.movementInput.leftKeyDown = false;
        }

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindRight.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()));
        if (Keyboard.isKeyDown(mc.gameSettings.keyBindRight.getKeyCode()))
        {
            --mc.player.movementInput.moveStrafe;
            mc.player.movementInput.rightKeyDown = true;
        }
        else
        {
            mc.player.movementInput.rightKeyDown = false;
        }

        KeyBinding.setKeyBindState(mc.gameSettings.keyBindJump.getKeyCode(), Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode()));
        mc.player.movementInput.jump = Keyboard.isKeyDown(mc.gameSettings.keyBindJump.getKeyCode());
    }
}
