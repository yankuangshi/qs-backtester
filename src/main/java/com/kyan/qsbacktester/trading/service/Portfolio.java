package com.kyan.qsbacktester.trading.service;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.SignalEvent;

/**
 * 组合接口
 *
 * @author kyan
 */
public interface Portfolio {

    /**
     * 根据行情事件更新组合内的市值，并生产一条旧的持仓信息加入到历史持仓中
     * @param market
     */
    void updateTimeIndex(MarketEvent market);

    /**
     * 根据策略信号事件生成下单事件
     * @param signal
     */
    void updateSignal(SignalEvent signal);

    /**
     * 根据回报事件更新组合内的仓位
     * @param fill
     */
    void updateFill(FillEvent fill);
}
