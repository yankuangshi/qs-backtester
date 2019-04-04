package com.kyan.qsbacktester.handler;

import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.framework.Handler;
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
