package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.trading.model.Bar;

import java.util.List;

/**
 * DataHandler interface
 *
 * @author kyan
 */
public interface DataHandler {

    /**
     * 获取股票的当前时间K线
     *
     * Get current bar
     *
     * @param symbol
     * @return
     */
    Bar getCurrentBar(String symbol);

    /**
     * 获取股票的当前时间K线的收盘价
     *
     * Get current bar's close price
     *
     * @param symbol
     * @return
     */
    double getCurrentBarClosePrice(String symbol);

    /**
     * 更新时间，并且把最新的行情事件（market event）放入事件队列（event queue）
     *
     * Update time, generate a market event and put it into queue
     */
    void updateCurrentTime();

    /**
     * 控制回测是否继续
     *
     * Test if backtest continue
     *
     * @return
     */
    boolean isContinueBacktest();

    /**
     * 获取所有股票代码
     *
     * Get symbol list
     * @return
     */
    List<String> getSymbols();

}
