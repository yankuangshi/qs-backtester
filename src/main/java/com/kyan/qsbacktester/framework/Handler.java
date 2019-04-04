package com.kyan.qsbacktester.framework;

public interface Handler<E extends Event> {

    //handle event
    void onEvent(E event);
}
