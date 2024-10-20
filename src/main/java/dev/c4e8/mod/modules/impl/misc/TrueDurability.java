package dev.c4e8.mod.modules.impl.misc;

import dev.c4e8.api.events.eventbus.EventHandler;
import dev.c4e8.api.events.impl.DurabilityEvent;
import dev.c4e8.mod.modules.Module;

public class TrueDurability extends Module {

    public TrueDurability() {
        super("TrueDurability", Category.Misc);
        setChinese("耐久度修正");
    }

    @EventHandler
    public void onDurability(DurabilityEvent event) {
        int dura = event.getItemDamage();
        if (event.getDamage() < 0) {
            dura = event.getDamage();
        }
        event.cancel();
        event.setDamage(dura);
    }
}
