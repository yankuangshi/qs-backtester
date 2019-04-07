package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.trading.model.Direction;
import com.kyan.qsbacktester.trading.model.OrderType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 下单事件（Order event）由组合（Portfolio）生成，
 * 并提供给下单系统（券商 Broker）由其执行下单
 *
 * 一个下单事件包含：
 *  symbol  股票代码
 *  orderType   价格类型（市价或限价）
 *  direction   方向（买或卖）
 *  quantity    交易数量
 *  orderTime   下单时间
 *
 * @author kyan
 */
@Slf4j
@Data
public class OrderEvent extends AbstractEvent {

    private String symbol;              // ticker symbol
    private OrderType orderType;        // MKT or LMT for market or limit order
    private Direction direction;        // BUY or SELL for long or short
    private Long quantity;              // the quantity of transaction
    private LocalDateTime orderTime;    // the timestamp at which the order is committed

    public OrderEvent(String symbol, OrderType orderType, Direction direction, Long quantity, LocalDateTime orderTime) {
        this.symbol = symbol;
        this.orderType = orderType;
        this.direction = direction;
        this.quantity = quantity;
        this.orderTime = orderTime;
    }

}
