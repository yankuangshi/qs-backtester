package com.kyan.qsbacktester.backtest;

import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.handler.*;
import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.OrderEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.framework.Event;
import com.kyan.qsbacktester.framework.EventDispatcher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Backtester {

    private EventDispatcher eventDispatcher;
    private EventQueue eventQueue;
    private DataHandler dataHandler;

    public Backtester(EventQueue eventQueue,
                      DataHandler dataHandler,
                      MarketEventHandler marketEventHandler,
                      SignalEventHandler signalEventHandler,
                      OrderEventHandler orderEventHandler,
                      FillEventHandler fillEventHandler) {
        this.eventQueue = eventQueue;
        this.dataHandler = dataHandler;
        this.eventDispatcher = new EventDispatcher();
        eventDispatcher.registerEventHandler(MarketEvent.class, marketEventHandler);
        eventDispatcher.registerEventHandler(OrderEvent.class, orderEventHandler);
        eventDispatcher.registerEventHandler(SignalEvent.class, signalEventHandler);
        eventDispatcher.registerEventHandler(FillEvent.class, fillEventHandler);
    }

    public void start() {
        log.info("Starts backtesting...");
        run();
        outputPerformance();
        log.info("Backtesting finished");
    }

    public void run() {
        while (true) {
            if (!this.dataHandler.isContinueBacktest()) {
                break;
            }
            this.dataHandler.update();
            while (!eventQueue.isEmpty()) {
                Event evt = eventQueue.get();
                eventDispatcher.dispatch(evt);
            }
        }
    }

    public void outputPerformance() {
        log.info("Prints performance...");
    }
}
