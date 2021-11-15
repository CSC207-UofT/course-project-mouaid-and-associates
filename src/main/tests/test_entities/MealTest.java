package test_entities;

import entities.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class MealTest {
    Meal meal;

    @BeforeEach
    void setUp(){
        this.meal = new Meal();
    }

    @Test
    void createScheduleIsEmptyByDefault(){
        this.meal.createSchedule();
        assertTrue(this.meal.getMySchedule().getEvents().isEmpty());
    }

    @Test
    void setScheduleTimes(){
        List<Double> times = new ArrayList<>();
        times.add(8.0);
        times.add(13.0);
        times.add(18.0);
        this.meal.setTime(times);
        assertFalse(this.meal.getMySchedule().getEvents().isEmpty());
    }
}
