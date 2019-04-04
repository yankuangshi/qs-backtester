package com.kyan.qsbacktesting.event;

import com.kyan.qsbacktesting.framework.StubEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testGetEventType() {
        StubEvent stubEvent = new StubEvent();
        assertEquals(StubEvent.class, stubEvent.getType());
    }
}
