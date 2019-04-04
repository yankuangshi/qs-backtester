package com.kyan.qsbacktesting.event;

import com.kyan.qsbacktesting.model.SignalType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class SignalEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private Long datetime;          // the datetime(ms) at which the signal was generated
    private SignalType signalType;   // LONG or SHORT

    public SignalEvent(String symbol, Long datetime, SignalType signalType) {
        log.info("Initialises a SignalEvent");
        this.symbol = symbol;
        this.datetime = datetime;
        this.signalType = signalType;
    }

}
