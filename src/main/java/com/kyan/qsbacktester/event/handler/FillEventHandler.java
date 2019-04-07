package com.kyan.qsbacktester.event.handler;

import com.kyan.qsbacktester.event.FillEvent;
import com.kyan.qsbacktester.framework.Handler;
import com.kyan.qsbacktester.trading.service.Portfolio;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FillEventHandler implements Handler<FillEvent> {

    private Portfolio portfolio;

    public FillEventHandler(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public void onEvent(FillEvent fill) {
        log.info("on fill event: {}", fill);
        //根据回报更新组合
        portfolio.updateFill(fill);
    }
}
