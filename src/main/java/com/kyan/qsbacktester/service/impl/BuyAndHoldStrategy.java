package com.kyan.qsbacktester.service.impl;

import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.handler.DataHandler;
import com.kyan.qsbacktester.model.Bar;
import com.kyan.qsbacktester.model.SignalType;
import com.kyan.qsbacktester.service.Strategy;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class BuyAndHoldStrategy implements Strategy {

    private EventQueue eventQueue;
    private DataHandler dataHandler;
    private Map<String, Boolean> bought;

    public BuyAndHoldStrategy(EventQueue eventQueue, DataHandler dataHandler) {
        this.eventQueue = eventQueue;
        this.dataHandler = dataHandler;
        initiateBought();
    }

    private void initiateBought() {
        Map<String, Boolean> result = new LinkedHashMap<>();
        for (String symbol : this.dataHandler.getSymbols()) {
            result.put(symbol, false);
        }
        this.bought = result;
    }

    @Override
    public void calculateSignals(MarketEvent market) {
        LocalDateTime currentTime = this.dataHandler.getCurrentTime();
        if (currentTime == null) {
            return;
        }
        for (String symbol : this.dataHandler.getSymbols()) {
            if (this.bought.get(symbol)) {
                continue;
            }
            Bar bar = this.dataHandler.getBar(symbol);
            if (bar == null) {
                continue;
            }
            this.bought.put(symbol, true);
            this.eventQueue.put(new SignalEvent(symbol, currentTime, SignalType.LONG));
        }

    }
}
