package dev.c4e8.mod.modules.impl.client;

import dev.c4e8.mod.modules.Module;
import dev.c4e8.mod.modules.settings.impl.ColorSetting;

import java.awt.*;

public class Colors extends Module {
    public static Colors INSTANCE;
    public Colors() {
        super("Colors", Category.Client);
        setChinese("颜色");
        INSTANCE = this;
    }

    public final ColorSetting clientColor = add(new ColorSetting("ClientColor", new Color(255, 0, 0)).injectBoolean(true));

    @Override
    public void enable() {
        this.state = true;
    }

    @Override
    public void disable() {
        this.state = true;
    }

    @Override
    public boolean isOn() {
        return true;
    }
}
