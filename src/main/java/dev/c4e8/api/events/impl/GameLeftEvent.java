package dev.c4e8.api.events.impl;

import dev.c4e8.api.events.Event;

public class GameLeftEvent extends Event {
    public GameLeftEvent() {
        super(Stage.Post);
    }
}
