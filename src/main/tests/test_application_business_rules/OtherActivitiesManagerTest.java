//package test_application_business_rules;
//
//import application_business_rules.OtherActivitiesManager;
//import entities.Meal;
//import entities.Sleep;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class OtherActivitiesManagerTest {
//
//    OtherActivitiesManager manager;
//
//    @BeforeEach
//    void setUp(){
//        this.manager = new OtherActivitiesManager();
//    }
//
//    @Test
//    void createSleepClass(){
//        assertTrue(this.manager.createNewSleepClass() instanceof Sleep);
//    }
//
//    @Test
//    void createMealClass(){
//        assertTrue(this.manager.createNewMealClass() instanceof Meal);
//    }
//
//    @Test
//    void getActivitySchedule(){
//        Sleep sleep = new Sleep();
//        assertEquals(manager.getActivitySchedule(sleep), sleep.getMySchedule());
//    }
//
//    @Test
//    void setActivityTimes(){
//        Sleep sleep = new Sleep();
//        List<Double> times = new ArrayList<>();
//        times.add(23.0);
//        times.add(7.0);
//        manager.setActivityTimes(sleep, times);
//        assertFalse(manager.getActivitySchedule(sleep).getEvents().isEmpty());
//    }
//}
