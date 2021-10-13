package application_business_rules;

import entities.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleCompiler {
    /**
     * Complies a list of schedules into one big schedule.
     * @param schedules     The list of schedules to be compiled.
     * @return     A big schedule that is a combination of all the
     *             other schedules.
     */
    public Schedule compileSchedules(List<Schedule> schedules){
        // Create an accumulator first.
        Schedule new_schedule = new Schedule(new ArrayList<>());

        // Iterate through the schedules
        for(Schedule schedule: schedules) {
            // Get events from each schedule and add them to the new schedule.
            new_schedule.addEvents(schedule.getEvents());
        }

        return new_schedule;
    }
}
