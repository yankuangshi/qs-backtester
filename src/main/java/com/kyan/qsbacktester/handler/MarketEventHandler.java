package com.kyan.qsbacktester.handler;

import com.kyan.qsbacktester.service.Strategy;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.framework.Handler;

public class MarketEventHandler implements Handler<MarketEvent> {

    private Strategy strategy;

    public MarketEventHandler(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void onEvent(MarketEvent event) {
        strategy.calculateSignals(event);
    }
}
