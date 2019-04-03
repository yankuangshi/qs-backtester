package com.github.kyan.handler;

import com.github.kyan.event.MarketEvent;
import com.github.kyan.framework.EventQueue;
import com.github.kyan.framework.Handler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomStrategy implements Handler<MarketEvent> {

    private EventQueue eventQueue;

    public RandomStrategy(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void onEvent(MarketEvent event) {
        log.info("Processing MarketEvent: {}", event);
    }
}
