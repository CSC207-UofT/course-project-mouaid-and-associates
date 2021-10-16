package test_application_business_rules;

import application_business_rules.ScheduleCompiler;
import entities.Event;
import entities.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleCompilerTest {
    ScheduleCompiler scheduleCompiler;
    List<Schedule> schedulesToTest;

    //TODO: Finish the unit tests.
    @BeforeEach
    void setUp() {
        scheduleCompiler = new ScheduleCompiler();
        schedulesToTest = new ArrayList<>();
        String[] dayList = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        for (int i  = 1; i < 6; i++){
            String name = "Test Schedule " + i;
        }


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void compileSchedules() {
    }
}