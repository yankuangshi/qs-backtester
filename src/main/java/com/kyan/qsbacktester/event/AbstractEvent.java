package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.framework.Event;

public abstract class AbstractEvent implements Event {

    @Override
    public Class<? extends Event> getType() {
        return getClass();
    }
}
