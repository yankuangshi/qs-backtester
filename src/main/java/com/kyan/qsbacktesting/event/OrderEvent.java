package com.kyan.qsbacktesting.event;

import com.kyan.qsbacktesting.model.Direction;
import com.kyan.qsbacktesting.model.OrderType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OrderEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private OrderType orderType;    // MKT or LMT for market or limit order
    private Direction direction;    // BUY or SELL for long or short
    private Long quantity;
    private Double price;           // valid when limit order
    private Long datetime;          // the timestamp at which the order is committed

    public OrderEvent(String symbol, OrderType orderType, Direction direction, Long quantity, Double price) {
        log.info("Initialises an OrderEvent");
        this.symbol = symbol;
        this.orderType = orderType;
        this.direction = direction;
        this.quantity = quantity;
        this.price = price;
    }

}
