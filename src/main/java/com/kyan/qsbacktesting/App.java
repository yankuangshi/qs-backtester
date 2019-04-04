package com.kyan.qsbacktesting;

import com.kyan.qsbacktesting.event.MarketEvent;
import com.kyan.qsbacktesting.framework.EventDispatcher;
import com.kyan.qsbacktesting.framework.EventQueue;
import com.kyan.qsbacktesting.handler.RandomStrategy;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        EventQueue eventQueue = EventQueue.getInstance();

        EventDispatcher dispatcher = new EventDispatcher();
        dispatcher.registerEventHandler(MarketEvent.class, new RandomStrategy(eventQueue));
        MarketEvent marketEvent = new MarketEvent();
        dispatcher.dispatch(marketEvent);
    }
}
