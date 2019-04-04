package com.kyan.qsbacktester.service.impl;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.service.Portfolio;

public class NaivePorfolio implements Portfolio {

    @Override
    public void updateSignal(SignalEvent signal) {

    }

    @Override
    public void updateFill(FillEvent fill) {

    }
}
