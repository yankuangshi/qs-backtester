package com.kyan.qsbacktester.trading.service.impl;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.event.MarketEvent;
import com.kyan.qsbacktester.event.OrderEvent;
import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.event.handler.DataHandler;
import com.kyan.qsbacktester.framework.EventQueue;
import com.kyan.qsbacktester.trading.model.Direction;
import com.kyan.qsbacktester.trading.model.OrderType;
import com.kyan.qsbacktester.trading.model.SignalType;
import com.kyan.qsbacktester.trading.service.Portfolio;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟组合持仓
 *
 * @author kyan
 */
@Slf4j
public class NaivePortfolio implements Portfolio {

    private EventQueue eventQueue;
    private DataHandler dataHandler;
    private Double initialCapital;  //初始资金
    private List<String> symbolList;
    private Map<LocalDateTime, Holding> holdingHistory;
    private Map<String, Long> currentPositions;
    private Double currentCash;
    private Double currentCommission;

    public NaivePortfolio(EventQueue eventQueue, DataHandler dataHandler, Double initialCapital) {
        this.eventQueue = eventQueue;
        this.dataHandler = dataHandler;
        this.initialCapital = initialCapital;
        this.holdingHistory = new LinkedHashMap<LocalDateTime, Holding>();
        this.symbolList = this.dataHandler.getSymbols();
        this.currentCash = initialCapital;      //初始化现金余额
        this.currentCommission = 0.0;           //初始化交易佣金
        currentPositions = new HashMap<String, Long>(); //初始化当前仓位
        for (String symbol : this.symbolList) {
            currentPositions.put(symbol, 0L);
        }
    }

    @Override
    public void updateTimeIndex(MarketEvent market) {
        double total = this.currentCash;
        Holding holding = new Holding();
        for (String symbol : symbolList) {
            long quantity = this.currentPositions.get(symbol);
            double closePrice = this.dataHandler.getCurrentBarClosePrice(symbol);
            if (closePrice == 0) {
                log.info("close price is 0 with symbol: {}, at: {}", symbol, market.getCurrentTime());
                throw new RuntimeException("close price is 0");
            }
            double marketValue = quantity * closePrice;
            holding.getPositions().put(symbol, quantity);
            holding.getMarketValues().put(symbol, marketValue);
            total += marketValue;
        }
        holding.setCash(this.currentCash);
        holding.setCommission(this.currentCommission);
        holding.setDateTime(market.getCurrentTime());
        holding.setTotal(total);
        log.info("current holding: {}", holding);
        holdingHistory.put(holding.getDateTime(), holding);
    }

    @Override
    public void updateSignal(SignalEvent signal) {
        OrderEvent order = generateNaiveOrder(signal);
        if (order != null) {
            log.info("generate order: {}", order);
            eventQueue.put(order);
        }
    }

    private OrderEvent generateNaiveOrder(SignalEvent signal) {
        long marketQuantity = 100 * signal.getLot();
        long currentQuantity = this.currentPositions.get(signal.getSymbol());

        if (signal.getSignalType() == SignalType.LONG && currentQuantity == 0) {
            //如果当前持仓为0， 买入100股
            return new OrderEvent(signal.getSymbol(), OrderType.MKT, Direction.BUY, marketQuantity, signal.getDatetime());
        }
        if (signal.getSignalType() == SignalType.SHORT && currentQuantity > marketQuantity) {
            //如果当前持仓大于100股，卖出100股
            return new OrderEvent(signal.getSymbol(), OrderType.MKT, Direction.SELL, marketQuantity, signal.getDatetime());
        }
        return null;
    }

    @Override
    public void updateFill(FillEvent fill) {
        updatePositionsFromFill(fill);
        updateHoldingFromFill(fill);
    }

    private void updatePositionsFromFill(FillEvent fill) {
        String symbol = fill.getSymbol();
        int fillDirection = fill.getDirection() == Direction.BUY ? 1 : -1;
        long currentQuantity = this.currentPositions.get(symbol);
        this.currentPositions.put(symbol, currentQuantity + fill.getQuantity() * fillDirection);
    }

    private void updateHoldingFromFill(FillEvent fill) {
        int fillDirection = fill.getDirection() == Direction.BUY ? 1 : -1;
        double commission = fill.getCommission();
        double cost = fill.getFillCost() * fillDirection;
        this.currentCash -= (cost + commission);
        this.currentCommission += commission;
    }
}
