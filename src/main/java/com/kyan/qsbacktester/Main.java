package com.kyan.qsbacktester;

import com.kyan.qsbacktester.backtest.Backtester;
import com.kyan.qsbacktester.event.handler.*;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.trading.service.Broker;
import com.kyan.qsbacktester.trading.service.Portfolio;
import com.kyan.qsbacktester.trading.service.Strategy;
import com.kyan.qsbacktester.trading.service.impl.BuyAndHoldStrategy;
import com.kyan.qsbacktester.trading.service.impl.NaivePortfolio;
import com.kyan.qsbacktester.trading.service.impl.SimulatedBroker;

public class Main {

    public static void main(String[] args) {
        EventQueue eventQueue = EventQueue.getInstance();
        String[] symbols = {"000002.SZ", "600000.SS"};
        DataHandler dataHandler = new HistoricalCSVDataHandler(eventQueue, symbols);
        Strategy bactestStrategy = new BuyAndHoldStrategy(eventQueue, dataHandler);
        Portfolio backtestPortfolio = new NaivePortfolio(eventQueue, dataHandler, 10000000.0);
        Broker backtestBroker = new SimulatedBroker(eventQueue, dataHandler);
        // 初始化事件处理器
        MarketEventHandler marketEventHandler = new MarketEventHandler(bactestStrategy, backtestPortfolio);
        SignalEventHandler signalEventHandler = new SignalEventHandler(backtestPortfolio);
        OrderEventHandler orderEventHandler = new OrderEventHandler(backtestBroker);
        FillEventHandler fillEventHandler = new FillEventHandler(backtestPortfolio);
        // run backtest
        Backtester backtester = new Backtester(eventQueue, dataHandler, marketEventHandler, signalEventHandler, orderEventHandler, fillEventHandler);
        backtester.start();
    }
}
