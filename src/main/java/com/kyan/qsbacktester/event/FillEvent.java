package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.model.Direction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class FillEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private LocalDateTime datetime;          // the timestamp at which the order is filled
    private Direction direction;    // BUY or SELL
    private Long quantity;          // the filled quantity
    private Double fillCost;       // the average cost price of filled order
    private Double commission;      // the commission of broker

    public FillEvent(String symbol, LocalDateTime datetime, Direction direction, Long quantity, Double fillCost) {
        this.symbol = symbol;
        this.datetime = datetime;
        this.direction = direction;
        this.quantity = quantity;
        this.fillCost = fillCost;
        this.commission = this.calculateCommission();
    }

    public FillEvent(String symbol, LocalDateTime datetime, Direction direction, Long quantity, Double fillCost, Double commission) {
        this.symbol = symbol;
        this.datetime = datetime;
        this.direction = direction;
        this.quantity = quantity;
        this.fillCost = fillCost;
        this.commission = commission;
    }

    private double calculateCommission() {
        return 0;
    }
}
