package com.kyan.qsbacktester.service;

import com.kyan.qsbacktester.event.OrderEvent;

public interface Broker {

    void executeOrder(OrderEvent order);
}
