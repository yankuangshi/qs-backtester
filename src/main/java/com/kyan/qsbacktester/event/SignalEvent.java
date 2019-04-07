package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.trading.model.SignalType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 信号事件（Signal event）由策略（Strategy）产生，
 * 并提供给组合（Portfolio）去生成下单事件（Order event）
 *
 * 一个信号事件包含：
 *  symbol      股票代码
 *  datetime    信号产生时间
 *  signalType  信号类型（做多或做空）
 *  lot         股票数量（单位：手）
 *
 * @author kyan
 */
@Slf4j
@Data
public class SignalEvent extends AbstractEvent {

    private String symbol;              // ticker symbol
    private LocalDateTime datetime;     // the datetime(ms) at which the signal was generated
    private SignalType signalType;      // LONG or SHORT
    private Integer lot;                // board lot

    public SignalEvent(String symbol, LocalDateTime datetime, SignalType signalType, Integer lot) {
        this.symbol = symbol;
        this.datetime = datetime;
        this.signalType = signalType;
        this.lot = lot;
    }

    public SignalEvent(String symbol, LocalDateTime datetime, SignalType signalType) {
        this.symbol = symbol;
        this.datetime = datetime;
        this.signalType = signalType;
        this.lot = 1;
    }

}
