package com.github.kyan.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventDispatcher<E extends Event> {

    private Map<Class<E>, List<Handler<E>>> _handlers;

    public EventDispatcher() {
        _handlers = new HashMap<>();
    }

    public void registerEventHandler(Class<E> eventType, Handler<E> handler) {
        if (_handlers.get(eventType) == null || _handlers.get(eventType).isEmpty()) {
            _handlers.put(eventType, new ArrayList<>());
        }
        _handlers.get(eventType).add(handler);
    }

    public boolean unregisterEventHandler(Class<E> eventType, Handler<E> handler) {
        if (_handlers.get(eventType) == null)
            return false;
        boolean ret = false;
        ret = _handlers.get(eventType).remove(handler);
        if (ret) {
            if (_handlers.get(eventType).isEmpty()) {
                _handlers.remove(eventType);
            }
        }
        return ret;
    }

    public void dispatch(E event) {
        List<Handler<E>> handlerList = _handlers.get(event.getClass());
        if (handlerList != null && !handlerList.isEmpty()) {
            for (Handler handler : handlerList) {
                handler.onEvent(event);
            }
        }
    }

}
