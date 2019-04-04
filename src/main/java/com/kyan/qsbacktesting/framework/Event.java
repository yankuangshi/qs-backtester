package com.kyan.qsbacktesting.framework;

public interface Event {

    Class<? extends Event> getType();
}
