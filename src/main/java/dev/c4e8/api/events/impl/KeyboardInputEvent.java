package dev.c4e8.api.events.impl;

import dev.c4e8.api.events.Event;

public class KeyboardInputEvent extends Event {
    public KeyboardInputEvent() {
        super(Stage.Pre);
    }
}
