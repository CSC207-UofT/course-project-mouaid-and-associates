package test_entities;

import entities.Event;
import entities.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleTest {
    Schedule schedule;
    List<Event> events;

    @BeforeEach
    void setUp(){
        String[] days = new String[]{"Monday", "Tuesday", "Wednesday"};
        double[] times = new double[]{2.0, 23.0, 15.5};
        events = new ArrayList<>();
        schedule = new Schedule(events);
        for (int i = 0; i < 3; i++) {
            String name = "Advil";
            String description = "Take Advil";
            schedule.addEvent(name, description, days[i], times[i]);
        }

    }

    @Test
    void addEvents() {
        // Create a list of disposable events.
        String[] days = new String[]{"Thursday", "Friday", "Saturday"};
        double[] times = new double[]{2.0, 23.0, 15.5};
        List<Event> events2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String name = "Advil";
            String description = "Take Advil";
            schedule.addEvent(name, description, days[i], times[i]);
        }

        // First make a copy of the events before change:
        List<Event> eventsCopy = new ArrayList<>(schedule.getEvents());

        // Now add the events to the schedule.
        schedule.addEvents(events2);

        // Now to ensure that the events in the schedule match the list of events outside.
        eventsCopy.addAll(events2);

        //Test to ensure the two lists are equal.
        assertEquals(eventsCopy, schedule.getEvents());

    }

    @Test
    void removeEvent() {
        // First select the event to be removed.
        Event removedEvent = events.get(2);

        // Remember that events and schedule.getEvents() are aliases. Thus make a copy.
        List<Event> eventsCopy = new ArrayList<>(events);
        schedule.removeEvent(removedEvent);
        eventsCopy.remove(removedEvent);
        assertEquals(eventsCopy, schedule.getEvents());
    }

    @Test
    void testToString() {
        String expected = new String("Monday: \n" +
                "    Advil\n    2:00 - Take Advil \n" +
                "Tuesday: \n" +
                "    Advil\n    23:00 - Take Advil \n" +
                "Wednesday: \n" +
                "    Advil\n    15:30 - Take Advil \n" +
                "Thursday: \n" +
                " NO EVENTS FOUND :( \n" +
                "Friday: \n" +
                " NO EVENTS FOUND :( \n"+
                "Saturday: \n" +
                " NO EVENTS FOUND :( \n"+
                "Sunday: \n" +
                " NO EVENTS FOUND :( \n");

        assertEquals(expected, schedule.toString());
    }

    @Test
    void sortEvents() {
        //TODO: Complete this test.
    }
}