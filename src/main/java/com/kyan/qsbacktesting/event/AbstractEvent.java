package com.kyan.qsbacktesting.event;

import com.kyan.qsbacktesting.framework.Event;

public abstract class AbstractEvent implements Event {

    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
}
