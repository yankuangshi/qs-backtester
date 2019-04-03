package com.github.kyan.framework;

public interface Event {

    Class<? extends Event> getType();
}
