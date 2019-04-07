package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.event.SignalEvent;
import com.kyan.qsbacktester.framework.Handler;
import com.kyan.qsbacktester.trading.service.Portfolio;
import lombok.extern.slf4j.Slf4j;

/**
 * 策略信号事件处理器
 */
@Slf4j
public class SignalEventHandler implements Handler<SignalEvent> {

    private Portfolio portfolio;

    public SignalEventHandler(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void onEvent(SignalEvent signal) {
        log.info("on signal event: {}", signal);
        portfolio.updateSignal(signal);
    }
}
