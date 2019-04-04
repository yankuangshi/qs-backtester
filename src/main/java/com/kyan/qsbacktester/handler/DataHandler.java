package com.kyan.qsbacktester.handler;

import com.kyan.qsbacktester.model.Bar;

import java.time.LocalDateTime;
import java.util.List;

public interface DataHandler {

    boolean isContinueBacktest();
    Bar getBar(String symbol);
    void update();
    List<String> getSymbols();
    LocalDateTime getCurrentTime();

}
