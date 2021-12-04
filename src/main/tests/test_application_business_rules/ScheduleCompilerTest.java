package test_application_business_rules;

import application_business_rules.ScheduleCompiler;
import entities.Event;
import entities.Schedule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScheduleCompilerTest {
    /**
     * Instance Attributes:
     * - scheduleCompiler: The schedule compiler object we need to test.
     * - schedulesToTest: The schedules we will pass in for compilation
     * -
     */
    ScheduleCompiler scheduleCompiler;
    List<Schedule> schedulesToTest;
    List<Event> masterEventsList;
    LocalDateTime timestamp;

    @BeforeEach
    void setUp() {
        scheduleCompiler = new ScheduleCompiler();
        schedulesToTest = new ArrayList<>();
        masterEventsList = new ArrayList<>();
        timestamp = LocalDateTime.parse("2021-12-12T10:25");


        for (int i  = 1; i < 6; i++){
            Schedule schedule = new Schedule();
            String name = "Test " + i;
            String description = "Test Event" + i;
            schedule.addEvent(name, description, timestamp);
            masterEventsList.addAll(schedule.getEvents());
            schedulesToTest.add(schedule);
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void compileSchedules() {
        // Compile the schedules
        Schedule compiledSchedule = scheduleCompiler.compileSchedules(schedulesToTest);

        // Note, when compiling the schedules, the events might not be in the same order, thus
        // we just need to make sure every event that appears in the master Event list shows up
        // in the compiled schedule. This ensures that we have every schedule compiled.
        List<Event> actualEvents = compiledSchedule.getEvents();
        for (Event event: masterEventsList){
            assertTrue(actualEvents.contains(event));
        }
    }
}