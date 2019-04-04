package com.kyan.qsbacktester.framework;

import com.kyan.qsbacktester.event.AbstractEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class StubEvent extends AbstractEvent {

    public StubEvent() {
        log.info("Initiate a StubEvent");
    }

}
