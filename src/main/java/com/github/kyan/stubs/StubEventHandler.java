package com.github.kyan.stubs;

import com.github.kyan.framework.Handler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StubEventHandler implements Handler<StubEvent> {

    @Override
    public void onEvent(StubEvent event) {
        log.info("Handle received event {}", event);
    }
}
