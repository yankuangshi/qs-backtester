package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.trading.service.Portfolio;
import com.kyan.qsbacktester.trading.service.Strategy;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.framework.Handler;
import lombok.extern.slf4j.Slf4j;

/**
 * 行情事件处理器
 */
@Slf4j
public class MarketEventHandler implements Handler<MarketEvent> {

    private Strategy strategy;
    private Portfolio portfolio;

    public MarketEventHandler(Strategy strategy, Portfolio portfolio) {
        this.strategy = strategy;
        this.portfolio = portfolio;
    }

    @Override
    public void onEvent(MarketEvent market) {
        log.info("on market event: {}", market);
        strategy.calculateSignals(market);
        portfolio.updateTimeIndex(market);
    }
}
