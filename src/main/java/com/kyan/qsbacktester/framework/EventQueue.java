package com.kyan.qsbacktester.framework;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {

    private static EventQueue instance;
    private Queue<Event> eventQueue;

    private EventQueue() {
        eventQueue = new LinkedBlockingQueue<Event>();
    }

    public static EventQueue getInstance() {
        if (instance == null)
            instance = new EventQueue();

        return instance;
    }

    public Event get() {
        return eventQueue.poll();
    }

    public void put(Event e) {
        eventQueue.offer(e);
    }

    public boolean isEmpty() {
        return eventQueue.isEmpty();
    }

    public int size() {
        return eventQueue.size();
    }
}