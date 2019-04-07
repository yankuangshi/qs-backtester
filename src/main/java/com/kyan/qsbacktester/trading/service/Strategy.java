package com.kyan.qsbacktester.trading.service;

import com.kyan.qsbacktester.event.MarketEvent;

/**
 * 策略接口
 *
 * @author kyan
 */
public interface Strategy {

    /**
     * 根据行情事件（market event）生成策略信号（signal event），并把信号加入事件队列
     * @param market
     */
    void calculateSignals(MarketEvent market);
}
