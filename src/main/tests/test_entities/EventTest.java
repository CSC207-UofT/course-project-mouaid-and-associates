package test_entities;

import entities.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    Event event;

    @BeforeEach
    void setUp() {
        LocalDateTime timeStamp = LocalDateTime.parse("2021-12-03T10:15");
        event = new Event("Tylenol", "Take Tylenol with Water.",
                timeStamp);
    }

    @Test
    void getTime() {
        String expected = "10:15";
        assertEquals(expected, event.getTime());
    }

    @Test
    void compareTo() {
        LocalDateTime timeStamp2 = LocalDateTime.parse("2021-12-03T10:25");
        Event event2 = new Event("Advil", "Swallow Advil",
                timeStamp2);
        int expected = -1;
        assertEquals(expected, event.compareTo(event2));
    }

    @Test
    void getDate(){
        String expected = "2021-12-03T10:15";
        assertEquals(expected, event.getDate());
    }
}