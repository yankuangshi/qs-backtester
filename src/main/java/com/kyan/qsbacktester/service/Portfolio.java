package com.kyan.qsbacktester.service;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.SignalEvent;

public interface Portfolio {

    void updateSignal(SignalEvent signal);
    void updateFill(FillEvent fill);
}
