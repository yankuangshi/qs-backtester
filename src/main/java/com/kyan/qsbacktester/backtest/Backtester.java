package com.kyan.qsbacktester.backtest;

import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.event.handler.*;
import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.OrderEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.framework.Event;
import com.kyan.qsbacktester.framework.EventDispatcher;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Backtester {

    private static final int HEART_BEATS_IN_MILLIS = 500;
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
        //注册事件处理器
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
            this.dataHandler.updateCurrentTime();
            while (!eventQueue.isEmpty()) {
                Event evt = eventQueue.get();
                eventDispatcher.dispatch(evt);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(HEART_BEATS_IN_MILLIS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void outputPerformance() {
        log.info("Prints performance...");
        //TODO
    }
}
