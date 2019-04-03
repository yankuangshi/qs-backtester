package com.github.kyan.event;

import com.github.kyan.model.enums.Direction;
import com.github.kyan.model.enums.FillState;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class FillEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private Direction direction;    // BUY or SELL
    private Long quantity;          // the filled quantity
    private Double commission;      // the commission of broker
    private Double costPrice;       // the average cost price of filled order
    private Long datetime;          // the timestamp at which the order is filled
    private FillState state;        // ALL, PARTIAL or CANCELED

    public FillEvent(String symbol, Direction direction, Long quantity, Double costPrice, Long datetime, FillState state) {
        log.info("Initialises a FillEvent");
        this.symbol = symbol;
        this.direction = direction;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.datetime = datetime;
        this.state = state;
    }

}
