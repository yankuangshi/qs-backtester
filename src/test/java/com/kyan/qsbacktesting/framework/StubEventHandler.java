package com.kyan.qsbacktesting.framework;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StubEventHandler implements EventHandler<StubEvent> {

    @Override
    public void onEvent(StubEvent event) {
        log.info("Handle received event {}", event);
    }
}
