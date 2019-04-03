package com.github.kyan.event;

import com.github.kyan.framework.Event;

public abstract class AbstractEvent implements Event {

    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
}
