package test_entities;

import entities.Sleep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SleepTest {

    Sleep sleep;

    @BeforeEach
    void setUp(){
        this.sleep = new Sleep();
    }

    @Test
    void createScheduleIsEmptyByDefault(){
        this.sleep.createSchedule();
        assertTrue(this.sleep.getMySchedule().getEvents().isEmpty());
    }
}