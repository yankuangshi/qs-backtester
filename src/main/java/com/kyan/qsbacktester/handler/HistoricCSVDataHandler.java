package com.kyan.qsbacktester.handler;

import com.kyan.qsbacktester.datasource.CSVDataSource;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.model.Bar;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HistoricCSVDataHandler implements DataHandler {

    private EventQueue eventQueue;
    private boolean continueBacktest;
    private CSVDataSource dataSource;
    private LocalDateTime currentTime;
    private Iterator<LocalDateTime> timeIterator;

    public HistoricCSVDataHandler(EventQueue eventQueue, CSVDataSource dataSource, LocalDateTime beginTime, LocalDateTime endTime) {
        this.eventQueue = eventQueue;
        this.dataSource = dataSource;
        this.continueBacktest = true;
        this.currentTime = null;
        setTimeIterator(beginTime, endTime);
    }


    private void setTimeIterator(LocalDateTime beginTime, LocalDateTime endTime) {
        List<LocalDateTime> dates = new ArrayList<>();
        dates.add(beginTime);
        while (beginTime.isBefore(endTime)) {
            beginTime = beginTime.plusDays(1);
            dates.add(beginTime);
        }
        this.timeIterator = dates.iterator();
    }


    @Override
    public Bar getBar(String symbol) {
        Map<LocalDateTime, Bar> bars = dataSource.getBars(symbol);

        if (this.currentTime == null) {
            return null;
        }
        if (bars.containsKey(currentTime)) {
            return bars.get(currentTime);
        }
        return null;
    }

    @Override
    public void update() {
        if (!this.continueBacktest) {
            return;
        }
        // update current time
        if (timeIterator.hasNext()) {
            currentTime = timeIterator.next();
        }
        if (currentTime != null) {
            // put market event into queue
            this.eventQueue.put(new MarketEvent(currentTime));
        } else {
            this.continueBacktest = false;
        }
    }

    @Override
    public boolean isContinueBacktest() {
        return continueBacktest;
    }

    @Override
    public List<String> getSymbols() {
        return dataSource.getSymbols();
    }

    @Override
    public LocalDateTime getCurrentTime() {
        return currentTime;
    }
}
