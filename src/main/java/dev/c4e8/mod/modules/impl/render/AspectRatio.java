package dev.c4e8.mod.modules.impl.render;

import dev.c4e8.mod.modules.settings.impl.SliderSetting;
import dev.c4e8.mod.modules.Module;

public class AspectRatio extends Module {
    public static AspectRatio INSTANCE;

    public final SliderSetting ratio =
            add(new SliderSetting("Ratio", 1.78, 0.0, 5.0, 0.01));
    public AspectRatio() {
        super("AspectRatio", Category.Render);
        setChinese("分辨率");
        INSTANCE = this;
    }
}
