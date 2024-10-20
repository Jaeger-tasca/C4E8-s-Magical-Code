package dev.c4e8.core.impl;

import dev.c4e8.api.utils.world.BlockUtil;
import dev.c4e8.C4E8;
import dev.c4e8.api.events.eventbus.EventHandler;
import dev.c4e8.api.events.eventbus.EventPriority;
import dev.c4e8.api.events.impl.TickEvent;
import dev.c4e8.mod.modules.impl.render.PlaceRender;

public class ThreadManager {
    public static ClientService clientService;

    public ThreadManager() {
        C4E8.EVENT_BUS.subscribe(this);
        clientService = new ClientService();
        clientService.setName("C4E8HackService");
        clientService.setDaemon(true);
        clientService.start();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEvent(TickEvent event) {
        if (event.isPre()) {
            if (!clientService.isAlive()) {
                clientService = new ClientService();
                clientService.setName("C4E8HackService");
                clientService.setDaemon(true);
                clientService.start();
            }
            BlockUtil.placedPos.forEach(pos -> PlaceRender.renderMap.put(pos, PlaceRender.INSTANCE.create(pos)));
            BlockUtil.placedPos.clear();
            C4E8.SERVER.onUpdate();
            C4E8.PLAYER.onUpdate();
            C4E8.MODULE.onUpdate();
            C4E8.GUI.onUpdate();
            C4E8.POP.onUpdate();
        }
    }

    public static class ClientService extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    if (C4E8.MODULE != null) {
                        C4E8.MODULE.onThread();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
