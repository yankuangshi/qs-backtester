package com.github.kyan;

import com.github.kyan.event.MarketEvent;
import com.github.kyan.framework.EventDispatcher;
import com.github.kyan.framework.EventQueue;
import com.github.kyan.handler.RandomStrategy;

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
