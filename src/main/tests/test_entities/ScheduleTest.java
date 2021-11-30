package test_entities;

import entities.Event;
import entities.Schedule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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
        LocalDateTime timestamp1 = LocalDateTime.parse("2021-12-03T10:15");
        LocalDateTime timestamp2 = LocalDateTime.parse("2021-12-04T10:15");
        LocalDateTime timestamp3 = LocalDateTime.parse("2021-12-05T10:15");
        LocalDateTime[] times = {timestamp1, timestamp2, timestamp3};
        events = new ArrayList<>();
        schedule = new Schedule(events);
        for (int i = 0; i < 3; i++) {
            String name = "Advil";
            String description = "Take Advil";
            schedule.addEvent(name, description, times[i]);
        }

    }

    @Test
    void addEvents() {
        Schedule schedule2 = new Schedule();
        // Create a list of disposable events.
        LocalDateTime timestamp1 = LocalDateTime.parse("2021-12-03T10:25");
        LocalDateTime timestamp2 = LocalDateTime.parse("2021-12-03T11:25");
        LocalDateTime timestamp3 = LocalDateTime.parse("2021-12-03T12:25");
        List<Event> events2 = new ArrayList<>();
        String name = "Advil";
        String description = "Take Advil";
        Event event1 = new Event(name, description, timestamp1);
        Event event2 = new Event(name, description, timestamp2);
        Event event3 = new Event(name, description, timestamp3);
        events2.add(event1);
        events2.add(event2);
        events2.add(event3);
        // Now add the events to the schedule.
        schedule2.addEvents(events2);
        assertEquals(true, schedule2.getEvents().contains(event1));
        assertEquals(true, schedule2.getEvents().contains(event2));
        assertEquals(true, schedule2.getEvents().contains(event3));
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

//    @Test
//    void testToString() {
//        String expected = new String("Monday: \n" +
//                "    Advil\n    2:00 - Take Advil \n" +
//                "Tuesday: \n" +
//                "    Advil\n    23:00 - Take Advil \n" +
//                "Wednesday: \n" +
//                "    Advil\n    15:30 - Take Advil \n" +
//                "Thursday: \n" +
//                "Nothing today \n" +
//                "Friday: \n" +
//                "Nothing today \n"+
//                "Saturday: \n" +
//                "Nothing today \n"+
//                "Sunday: \n" +
//                "Nothing today \n");
//
//        assertEquals(expected, schedule.toString());
//    }

    @Test
    void addEvent() {
        LocalDateTime newTimeStamp = LocalDateTime.parse("2021-12-12T10:25");
        Event expected = new Event("Advil", "Take Advil", newTimeStamp);
        // Clear all the events to ensure there is only one event in the schedule.
        // We are just checking if the event is added.
        schedule.removeAllEvents();
        schedule.addEvent("Advil", "Take Advil", newTimeStamp);
        Event actual = schedule.getEvents().get(0);
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getTime(), actual.getTime());
    }

    @Test
    void removeAllEvents() {
        schedule.removeAllEvents();
        //Check if the list of events is empty
        assertEquals(schedule.getEvents(), new ArrayList<>());
    }

    @Test
    void getNumberOfEvents() {
        int expected = 3;
        assertEquals(expected, schedule.getEvents().size());
    }

    @Test
    void getEventTimes() {
        String[] expected = new String[]{"2021-12-03T10:15", "2021-12-04T10:15", "2021-12-05T10:15"};
        String[] actual = schedule.getEventTimes();
        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void setEventTimes() {
        LocalDateTime newTime1 = LocalDateTime.parse("2021-12-12T10:25");
        LocalDateTime newTime2 = LocalDateTime.parse("2021-12-13T10:25");
        List<LocalDateTime> times = new ArrayList<>();
        times.add(newTime1);
        times.add(newTime2);
        schedule.setEventTimes(times);

        String[] expected = new String[]{"2021-12-12T10:25", "2021-12-13T10:25"};
        String[] actual = schedule.getEventTimes();
        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void setEventNames() {
        String expected = "Tylenol";
        schedule.setEventNames(expected);
        for (Event actual_event: schedule.getEvents()){
            assertEquals(expected, actual_event.getName());
        }
    }

    @Test
    void setEventDescriptions() {
        String expected = "Drink Tylenol";
        schedule.setEventDescriptions(expected);
        for (Event actual_event: schedule.getEvents()){
            assertEquals(expected, actual_event.getDescription());
        }
    }
}