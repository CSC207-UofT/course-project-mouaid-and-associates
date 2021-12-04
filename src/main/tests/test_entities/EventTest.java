//package test_entities;
//
//import entities.Event;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class EventTest {
//
//    Event event;
//
//    @BeforeEach
//    void setUp() {
//        Map<String, Double> timeStamp = new HashMap<>();
//        timeStamp.put("Monday", 10.5);
//        event = new Event("Tylenol", "Take Tylenol with Water.",
//                timeStamp);
//    }
//
//    @Test
//    void getHour() {
//        double expected = 10.5;
//        assertEquals(expected, event.getHour());
//    }
//
//    @Test
//    void compareTo() {
//        Map<String, Double> timeStamp2 = new HashMap<>();
//        timeStamp2.put("Monday", 11.5);
//        Event event2 = new Event("Advil", "Swallow Advil",
//                timeStamp2);
//        int expected = -1;
//        assertEquals(expected, event.compareTo(event2));
//    }
//
//    @Test
//    void decimalToHourFormat() {
//        String expected = "10:30";
//        assertEquals(expected, event.decimalToHourFormat());
//    }
//}