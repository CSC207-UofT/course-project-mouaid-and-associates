//package test_entities;
//
//import entities.Event;
//import entities.Schedule;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ScheduleTest {
//    Schedule schedule;
//    List<Event> events;
//
//    @BeforeEach
//    void setUp(){
//        String[] days = new String[]{"Monday", "Tuesday", "Wednesday"};
//        double[] times = new double[]{2.0, 23.0, 15.5};
//        events = new ArrayList<>();
//        schedule = new Schedule(events);
//        for (int i = 0; i < 3; i++) {
//            String name = "Advil";
//            String description = "Take Advil";
//            schedule.addEvent(name, description, days[i], times[i]);
//        }
//
//    }
//
//    @Test
//    void addEvents() {
//        // Create a list of disposable events.
//        String[] days = new String[]{"Thursday", "Friday", "Saturday"};
//        double[] times = new double[]{2.0, 23.0, 15.5};
//        List<Event> events2 = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            String name = "Advil";
//            String description = "Take Advil";
//            schedule.addEvent(name, description, days[i], times[i]);
//        }
//
//        // First make a copy of the events before change:
//        List<Event> eventsCopy = new ArrayList<>(schedule.getEvents());
//
//        // Now add the events to the schedule.
//        schedule.addEvents(events2);
//
//        // Now to ensure that the events in the schedule match the list of events outside.
//        eventsCopy.addAll(events2);
//
//        //Test to ensure the two lists are equal.
//        assertEquals(eventsCopy, schedule.getEvents());
//
//    }
//
//    @Test
//    void removeEvent() {
//        // First select the event to be removed.
//        Event removedEvent = events.get(2);
//
//        // Remember that events and schedule.getEvents() are aliases. Thus make a copy.
//        List<Event> eventsCopy = new ArrayList<>(events);
//        schedule.removeEvent(removedEvent);
//        eventsCopy.remove(removedEvent);
//        assertEquals(eventsCopy, schedule.getEvents());
//    }
//
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
//
//    @Test
//    void addEventNoMapping() {
//        Map<String, Double> time = new HashMap<>();
//        time.put("Monday", 10.0);
//        Event expected = new Event("Advil", "Take Advil", time);
//        // Clear all the events to ensure there is only one event in the schedule.
//        // We are just checking if the event is added.
//        schedule.removeAllEvents();
//        schedule.addEvent("Advil", "Take Advil", "Monday", 10.0);
//        Event actual = schedule.getEvents().get(0);
//
//        assertEquals(expected.getName(), actual.getName());
//        assertEquals(expected.getDescription(), actual.getDescription());
//        assertEquals(expected.decimalToHourFormat(), actual.decimalToHourFormat());
//    }
//
//    @Test
//    void addEventWithMapping() {
//        Map<String, Double> time = new HashMap<>();
//        time.put("Monday", 10.0);
//        Event expected = new Event("Advil", "Take Advil", time);
//        // Clear all the events to ensure there is only one event in the schedule.
//        // We are just checking if the event is added.
//        schedule.removeAllEvents();
//        schedule.addEvent("Advil", "Take Advil", time);
//        Event actual = schedule.getEvents().get(0);
//
//        assertEquals(expected.getName(), actual.getName());
//        assertEquals(expected.getDescription(), actual.getDescription());
//        assertEquals(expected.decimalToHourFormat(), actual.decimalToHourFormat());
//    }
//
//    @Test
//    void removeAllEvents() {
//        schedule.removeAllEvents();
//        //Check if the list of events is empty
//        assertEquals(schedule.getEvents(), new ArrayList<>());
//    }
//
//    @Test
//    void getNumberOfEvents() {
//        int expected = 3;
//        assertEquals(expected, schedule.getEvents().size());
//    }
//
//    @Test
//    void getEventTimes() {
//        String[] expected = new String[]{"Monday: 2:00", "Tuesday: 23:00", "Wednesday: 15:30"};
//        String[] actual = schedule.getEventTimes();
//        for (int i = 0; i < expected.length; i++){
//            assertEquals(expected[i], actual[i]);
//        }
//    }
//
//    @Test
//    void setEventTimes() {
//        Map<String, Double> newTime1 = new HashMap<>();
//        Map<String, Double> newTime2 = new HashMap<>();
//        newTime1.put("Thursday", 10.0);
//        newTime2.put("Thursday", 15.0);
//        List<Map<String, Double>> times = new ArrayList<>();
//        times.add(newTime1);
//        times.add(newTime2);
//        schedule.setEventTimes(times);
//
//        String[] expected = new String[]{"Thursday: 10:00", "Thursday: 15:00"};
//        String[] actual = schedule.getEventTimes();
//        for (int i = 0; i < expected.length; i++){
//            assertEquals(expected[i], actual[i]);
//        }
//    }
//
//    @Test
//    void setEventNames() {
//        String expected = "Tylenol";
//        schedule.setEventNames(expected);
//        for (Event actual_event: schedule.getEvents()){
//            assertEquals(expected, actual_event.getName());
//        }
//    }
//
//    @Test
//    void setEventDescriptions() {
//        String expected = "Drink Tylenol";
//        schedule.setEventDescriptions(expected);
//        for (Event actual_event: schedule.getEvents()){
//            assertEquals(expected, actual_event.getDescription());
//        }
//    }
//}