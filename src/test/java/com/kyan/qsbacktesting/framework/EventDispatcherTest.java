package com.kyan.qsbacktesting.framework;

import org.junit.Test;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class EventDispatcherTest {

    @Test
    public void testEventDispatcher() {
        EventDispatcher dispatcher = spy(new EventDispatcher());
        StubEventHandler stubEventHandler = spy(new StubEventHandler());
        dispatcher.registerEventHandler(StubEvent.class, stubEventHandler);

        StubEvent stubEvent = new StubEvent();
        dispatcher.dispatch(stubEvent);

        verify(stubEventHandler).onEvent(stubEvent);
        verify(dispatcher).dispatch(stubEvent);
    }
}
