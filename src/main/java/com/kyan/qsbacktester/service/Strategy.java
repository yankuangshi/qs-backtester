package com.kyan.qsbacktester.service;

import com.kyan.qsbacktester.event.MarketEvent;

public interface Strategy {

    void calculateSignals(MarketEvent market);
}
