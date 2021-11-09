package application_business_rules;

import entities.Sleep;
import entities.SleepSchedule;

import java.util.List;

public class SleepManager {
    /**
     * This class manages the SleepSchedule and the MealSchedule
     */
    public SleepManager(){}

    /**
     * Create a default Sleep Class with an empty SleepSchedule
     * @return The created Sleep class
     */
    public Sleep createNewSleepClass(){
        Sleep sleepClass = new Sleep();
        sleepClass.createSleepSchedule();
        return sleepClass;
    }

    /**
     * Get the SleepSchedule of the given Sleep Class
     * @param sleep The Sleep class to get the SleepSchedule from
     * @return This Sleep Class' SleepSchedule
     */
    public SleepSchedule getSleepSchedule(Sleep sleep) {
        return sleep.getMySleepSchedule();
    }

    /**
     * Set the Sleep and wakeup times of the given Sleep Class
     * @param sleep The Sleep class to set the Sleep and wakeup times for
     * @param times A list containing the new sleep time and wakeup times
     */
    public void setSleepAndWakeUpTimes(Sleep sleep, List<Double> times){
        sleep.setSleepAndWakeUpTime(times);
    }
}
