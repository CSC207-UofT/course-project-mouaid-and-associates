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
}
