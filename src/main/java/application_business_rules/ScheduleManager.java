package application_business_rules;

import entities.Schedule;

import java.util.List;
import java.util.Map;

public class ScheduleManager {
    /**
     * The class that manages the final schedule
     * Instance Attributes:
     * finalSchedule: The main schedule that will be managed by ScheduleManager
     */
    private Schedule finalSchedule;

    /**
     * Compiles a bunch of schedule passed in.
     * @param schedules     All the schedules to be compiled.
     * @return      The final schedule made from compiling all the schedules.
     */
    public Schedule compileSchedule(List<Schedule> schedules){
        ScheduleCompiler scheduleCompiler = new ScheduleCompiler();

        // Store the compiled schedule in this class.
        this.finalSchedule = scheduleCompiler.compileSchedules(schedules);

        return finalSchedule;
    }

    /**
     * Edits the event times of a given schedule, using the given times.
     *
     * Preconditions:
     * - The event names and descriptions of the given schedule are all the same.
     * - The number of events is equal to the number of elements in times.
     * @param schedule      The schedule to be edited
     * @param times         The new time stamps
     */
    public void editScheduleTimes(Schedule schedule, List<Map<String, Double>> times){
        if (times.size() > 0) {
            schedule.setEventTimes(times);
        }
    }

}
