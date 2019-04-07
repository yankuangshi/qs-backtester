package com.kyan.qsbacktester.trading.service.impl;

import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.event.handler.DataHandler;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.trading.model.SignalType;
import com.kyan.qsbacktester.trading.service.Strategy;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 买入并持有策略
 *
 * @author kyan
 */
@Slf4j
public class BuyAndHoldStrategy implements Strategy {

    private EventQueue eventQueue;
    private DataHandler dataHandler;
    private Map<String, Boolean> bought;    //记录买入的标的

    public BuyAndHoldStrategy(EventQueue eventQueue, DataHandler dataHandler) {
        this.eventQueue = eventQueue;
        this.dataHandler = dataHandler;
        initiateBought();
    }

    /**
     * 初始化买入标的
     */
    private void initiateBought() {
        Map<String, Boolean> result = new LinkedHashMap<>();
        for (String symbol : this.dataHandler.getSymbols()) {
            result.put(symbol, false);
        }
        this.bought = result;
    }

    @Override
    public void calculateSignals(MarketEvent market) {
        LocalDateTime currentTime = market.getCurrentTime();
        if (currentTime == null) {
            return;
        }
        for (String symbol : this.dataHandler.getSymbols()) {
            if (this.dataHandler.getCurrentBar(symbol) == null) {
                continue;
            }
            if (this.dataHandler.getCurrentBar(symbol).getVolume() == 0) {
                //bar不为空且volume为0，说明为停牌，不产生策略信号
                continue;
            }
            this.bought.put(symbol, true);
            SignalEvent signal = new SignalEvent(symbol, currentTime, SignalType.LONG);
            log.info("generate signal: {}", signal);
            this.eventQueue.put(signal);
        }

    }
}
