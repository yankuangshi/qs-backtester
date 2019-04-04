package com.kyan.qsbacktester.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Data
public class MarketEvent extends AbstractEvent {

    private LocalDateTime dateTime;

    public MarketEvent(LocalDateTime dateTime) {
        log.info("Initialises a MarketEvent");
        this.dateTime = dateTime;
    }

}
