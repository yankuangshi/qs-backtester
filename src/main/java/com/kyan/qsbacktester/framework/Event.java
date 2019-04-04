package com.kyan.qsbacktester.framework;

public interface Event {

    Class<? extends Event> getType();
}
