package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.event.OrderEvent;
import com.kyan.qsbacktester.framework.Handler;
import com.kyan.qsbacktester.trading.service.Broker;
import lombok.extern.slf4j.Slf4j;

/**
 * 下单事件处理器
 */
@Slf4j
public class OrderEventHandler implements Handler<OrderEvent> {

    private Broker broker;

    public OrderEventHandler(Broker broker) {
        this.broker = broker;
    }

    @Override
    public void onEvent(OrderEvent order) {
        log.info("on order event: {}", order);
        broker.executeOrder(order);
    }
}
