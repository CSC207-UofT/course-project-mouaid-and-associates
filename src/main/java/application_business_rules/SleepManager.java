package application_business_rules;

import entities.Sleep;
import entities.SleepSchedule;

import java.util.List;

public class SleepManager {
    /**
     * This class manages the SleepSchedule and the MealSchedule
     */
    public SleepManager(){}

    public Sleep createNewSleepClass(List<Double> times){
        Sleep sleepClass = new Sleep(times);
        sleepClass.createSleepSchedule();
        return sleepClass;
    }

    public SleepSchedule getSleepSchedule(Sleep sleep) {
        return sleep.getMySleepSchedule();
    }

    public List<Double> getSleepAndWakeUpTimes(Sleep sleep){
        return sleep.getSleepAndWakeUpTime();
    }

    public void setSleepAndWakeUpTimes(Sleep sleep, List<Double> times){
        sleep.setSleepAndWakeUpTime(times);
    }
}
