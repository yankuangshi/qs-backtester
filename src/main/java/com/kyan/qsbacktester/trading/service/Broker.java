package com.kyan.qsbacktester.trading.service;

import com.kyan.qsbacktester.event.OrderEvent;

/**
 * 下单执行系统接口（券商）
 *
 * @author kyan
 */
public interface Broker {

    /**
     * 获取下单事件，执行下单，并生产下单回报事件放入事件队列
     *
     * takes an order event and executes it, producing
     * a fill event that gets places onto the event queue
     * @param order
     */
    void executeOrder(OrderEvent order);
}
