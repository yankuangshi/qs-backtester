package com.kyan.qsbacktesting.handler;

import com.kyan.qsbacktesting.event.MarketEvent;
import com.kyan.qsbacktesting.framework.EventQueue;
import com.kyan.qsbacktesting.framework.EventHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RandomStrategy implements EventHandler<MarketEvent> {

    private EventQueue eventQueue;

    public RandomStrategy(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    @Override
    public void onEvent(MarketEvent event) {
        log.info("Processing MarketEvent: {}", event);
    }
}
