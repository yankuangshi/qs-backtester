package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.trading.model.Direction;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 下单回报事件（Fill event）由下单系统（券商 Broker）返回，并提供给组合（Portfolio）来更新仓位
 *
 * 一个下单回报事件包含：
 *  symbol      股票代码
 *  fillTime    下单回报时间
 *  direction   方向（买或卖）
 *  quantity    实际成交数量
 *  fillCost    成本
 *  commission  交易佣金
 *
 * @author kyan
 */
@Slf4j
@Data
public class FillEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private LocalDateTime fillTime; // the timestamp at which the order is filled
    private Direction direction;    // BUY or SELL
    private Long quantity;          // the filled quantity
    private Double fillCost;        // the average cost price of filled order
    private Double commission;      // the commission of broker

    public FillEvent(String symbol, LocalDateTime fillTime, Direction direction, Long quantity, Double fillCost) {
        this.symbol = symbol;
        this.fillTime = fillTime;
        this.direction = direction;
        this.quantity = quantity;
        this.fillCost = fillCost;
        this.commission = this.calculateCommission();
    }

    public FillEvent(String symbol, LocalDateTime fillTime, Direction direction, Long quantity, Double fillCost, Double commission) {
        this.symbol = symbol;
        this.fillTime = fillTime;
        this.direction = direction;
        this.quantity = quantity;
        this.fillCost = fillCost;
        this.commission = commission;
    }

    private double calculateCommission() {
        // You can implement your own commission calculation here
        return 0;
    }
}
