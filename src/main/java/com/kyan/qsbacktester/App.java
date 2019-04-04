package com.kyan.qsbacktester;

import com.kyan.qsbacktester.backtest.Backtester;
import com.kyan.qsbacktester.datasource.CSVDataSource;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.handler.*;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        EventQueue eventQueue = EventQueue.getInstance();
        CSVDataSource csvDataSource = new CSVDataSource();
        String dirPath = Paths.get("src", "main", "resources").toString() + File.separator + "histdata";
        csvDataSource.loadBarsFromCSV(dirPath, "000001.SZ", "000002.SZ");
        LocalDateTime beginTime = null, endTime = null;
        DataHandler dataHandler = new HistoricCSVDataHandler(eventQueue, csvDataSource, beginTime, endTime);
        MarketEventHandler marketEventHandler = new MarketEventHandler();
        SignalEventHandler signalEventHandler = new SignalEventHandler();
        OrderEventHandler orderEventHandler = new OrderEventHandler();
        FillEventHandler fillEventHandler = new FillEventHandler();
        Backtester backtester = new Backtester(eventQueue, dataHandler, marketEventHandler, signalEventHandler, orderEventHandler, fillEventHandler);
        backtester.start();
    }
}
