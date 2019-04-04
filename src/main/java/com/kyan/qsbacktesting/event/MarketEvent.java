package com.kyan.qsbacktesting.event;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class MarketEvent extends AbstractEvent {

    public MarketEvent() {
        log.info("Initialises a MarketEvent");
    }

}
