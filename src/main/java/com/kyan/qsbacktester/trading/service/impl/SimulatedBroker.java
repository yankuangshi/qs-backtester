package com.kyan.qsbacktester.trading.service.impl;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.OrderEvent;
import com.kyan.qsbacktester.event.handler.DataHandler;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.trading.service.Broker;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 模拟交易下单系统
 *
 * @author kyan
 */
@Slf4j
public class SimulatedBroker implements Broker {

    private static final int SIMULATED_EXECUTION_DELAY_IN_SECONDS = 1;

    private EventQueue eventQueue;
    private DataHandler dataHandler;

    public SimulatedBroker(EventQueue eventQueue, DataHandler dataHandler) {
        this.eventQueue = eventQueue;
        this.dataHandler = dataHandler;
    }

    @Override
    public void executeOrder(OrderEvent order) {
        log.info("execute order: {}", order);
        //delay the fill time
        LocalDateTime fillTime = order.getOrderTime().plusSeconds(SIMULATED_EXECUTION_DELAY_IN_SECONDS);
        double closePrice = dataHandler.getCurrentBarClosePrice(order.getSymbol());
        if (closePrice == 0) {
            log.error("close price is 0 with symbol: {}, at: {}", order.getSymbol(), order.getOrderTime());
            throw new RuntimeException("close price is 0");
        }
        double fillCost = closePrice * order.getQuantity();
        FillEvent fill = new FillEvent(order.getSymbol(), fillTime, order.getDirection(), order.getQuantity(), fillCost);
        log.info("generate fill: {}", fill);
        this.eventQueue.put(fill);
    }
}
