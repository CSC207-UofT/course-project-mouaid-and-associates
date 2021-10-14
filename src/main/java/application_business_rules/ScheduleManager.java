package application_business_rules;

import entities.Schedule;

import java.util.List;

public class ScheduleManager {
    private Schedule finalSchedule;

    public void setFinalSchedule(Schedule finalSchedule) {
        this.finalSchedule = finalSchedule;
    }

    public Schedule getFinalSchedule() {
        return finalSchedule;
    }

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

}
