package com.kyan.qsbacktesting.framework;

public interface EventHandler<E extends Event> {

    //handle event
    void onEvent(E event);
}
