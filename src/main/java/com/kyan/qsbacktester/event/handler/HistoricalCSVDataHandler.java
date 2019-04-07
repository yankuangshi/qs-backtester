package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.datasource.CSVDataSource;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.trading.model.Bar;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

public class HistoricalCSVDataHandler implements DataHandler {

    private static final String DIR_PATH = Paths.get("src", "main", "resources").toString() + File.separator + "histdata";
    private EventQueue eventQueue;
    private boolean continueBacktest;
    private CSVDataSource dataSource;                   //数据源，该类中的数据源从CSV文件获取
    private LocalDateTime currentTime;                  //当前时间
    private Iterator<LocalDateTime> timeIndexIterator;  //日期迭代器，用于遍历历史日期

    public HistoricalCSVDataHandler(EventQueue eventQueue, String... symbols) {
        this.eventQueue = eventQueue;
        this.continueBacktest = true;
        this.currentTime = null;
        this.dataSource = initiateSymbolData(symbols);
        setTimeIndexIterator(); //设置日期迭代器
    }

    private CSVDataSource initiateSymbolData(String... symbols) {
        CSVDataSource csvDataSource = new CSVDataSource();
        csvDataSource.loadBarsFromCSV(DIR_PATH, symbols);
        return csvDataSource;
    }


    private void setTimeIndexIterator() {
        //获取所有股票的交易日
        Set<LocalDateTime> dateSet = new HashSet<>();
        for (Map<LocalDateTime, Bar> bars : dataSource.getHistData().values()) {
            dateSet.addAll(bars.keySet());
        }
        List<LocalDateTime> dates = new ArrayList<>(dateSet);
        Collections.sort(dates);
        this.timeIndexIterator = dates.iterator();
    }


    @Override
    public Bar getCurrentBar(String symbol) {
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
    public double getCurrentBarClosePrice(String symbol) {
        Bar bar = getCurrentBar(symbol);
        if (bar == null) {
            return 0;
        }
        return bar.getClose();
    }

    @Override
    public void updateCurrentTime() {
        if (!this.continueBacktest) {
            return;
        }
        // update current time
        if (timeIndexIterator.hasNext()) {
            currentTime = timeIndexIterator.next();
        } else {
            currentTime = null;
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

}
