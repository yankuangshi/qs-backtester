package com.kyan.qsbacktester.event;

import com.kyan.qsbacktester.framework.StubEvent;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EventTest {

    @Test
    public void testGetEventType() {
        StubEvent stubEvent = new StubEvent();
        assertEquals(StubEvent.class, stubEvent.getType());
    }
}
