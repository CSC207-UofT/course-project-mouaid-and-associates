package application_business_rules;

import entities.Schedule;

import java.util.List;

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

//    /**
//     * Sets the finalSchedule managed by ScheduleManager
//     * @param finalSchedule the schedule to be as the finalSchedule managed by ScheduleManager
//     */
//    public void setFinalSchedule(Schedule finalSchedule) {
//        this.finalSchedule = finalSchedule;
//    }
//
//    /**
//     * Gets the finalSchedule managed by ScheduleManager.
//     * @return  the finalSchedule managed by ScheduleManager.
//     */
//    public Schedule getFinalSchedule() {
//        return finalSchedule;
//    }

}
