package com.github.kyan.framework;

public interface Handler<E extends Event> {

    void onEvent(E event);
}
