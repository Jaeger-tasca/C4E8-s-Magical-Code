package dev.c4e8.mod.gui.clickgui;

import dev.c4e8.C4E8;
import dev.c4e8.mod.gui.clickgui.tabs.Tab;
import dev.c4e8.mod.modules.settings.impl.SliderSetting;
import dev.c4e8.mod.modules.settings.impl.StringSetting;
import dev.c4e8.api.utils.Wrapper;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen implements Wrapper {

    public ClickGuiScreen() {
        super(Text.of("ClickGui"));
    }
    public static boolean clicked = false;
    public static boolean rightClicked = false;
    public static boolean hoverClicked = false;

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public void render(DrawContext drawContext, int mouseX, int mouseY, float partialTicks) {
        super.render(drawContext, mouseX, mouseY, partialTicks);
        C4E8.GUI.draw(mouseX, mouseY, drawContext, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        C4E8.MODULE.modules.forEach(module -> module.getSettings().stream()
                .filter(setting -> setting instanceof StringSetting)
                .map(setting -> (StringSetting) setting)
                .filter(StringSetting::isListening)
                .forEach(setting -> setting.keyType(keyCode)));
        C4E8.MODULE.modules.forEach(module -> module.getSettings().stream()
                .filter(setting -> setting instanceof SliderSetting)
                .map(setting -> (SliderSetting) setting)
                .filter(SliderSetting::isListening)
                .forEach(setting -> setting.keyType(keyCode)));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            hoverClicked = false;
            clicked = true;
        } else if (button == 1) {
            rightClicked = true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            clicked = false;
            hoverClicked = false;
        } else if (button == 1) {
            rightClicked = false;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public void close() {
        super.close();
        rightClicked = false;
        hoverClicked = false;
        clicked = false;
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        for (Tab tab : C4E8.GUI.tabs) {
            tab.setY((int) (tab.getY() + (verticalAmount * 30)));
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }
}
