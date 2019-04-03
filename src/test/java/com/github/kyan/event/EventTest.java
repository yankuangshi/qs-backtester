package com.github.kyan.event;

import com.github.kyan.stubs.StubEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testGetEventType() {
        StubEvent stubEvent = new StubEvent();
        assertEquals(StubEvent.class, stubEvent.getType());
    }
}
