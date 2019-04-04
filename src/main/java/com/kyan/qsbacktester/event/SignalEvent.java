package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.model.SignalType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class SignalEvent extends AbstractEvent {

    private String symbol;          // ticker symbol
    private LocalDateTime datetime;          // the datetime(ms) at which the signal was generated
    private SignalType signalType;   // LONG or SHORT

    public SignalEvent(String symbol, LocalDateTime datetime, SignalType signalType) {
        log.info("Initialises a SignalEvent");
        this.symbol = symbol;
        this.datetime = datetime;
        this.signalType = signalType;
    }

}
