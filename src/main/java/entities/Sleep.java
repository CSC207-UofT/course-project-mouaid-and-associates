package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sleep extends OtherActivities{
    /**
     * This is a Class that stores the Does everything related to Sleeping.
     * Instance Attributes:
     * sleepSchedule: This Sleep Class' SleepSchedule
     */

    private Schedule schedule;
    private List<Double> times;


    public Sleep() {
        super();
        this.times = new ArrayList<>();
    }

    /**
     * Creates a SleepSchedule for this Sleep Class. The SleepSchedule is empty if the class is being instantiated for
     * the first time and is not empty after the user sets Sleep and wake up times
     */
    public void createSchedule(){
        String[]  daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        this.schedule = new OtherActivitiesSchedule();

        // Checks if the sleep and wake up times isEmpty() which indicates that the SleepClass is being created for
        // the first time and therefore there are no set Sleep and wake up times so the SleepSchedule is empty
        if (!(times.isEmpty())) {
            for (String i : daysOfWeek) {
                Map<String, Double> sleep = new HashMap<>();
                Map<String, Double> wake = new HashMap<>();
                sleep.put(i, this.times.get(0));
                wake.put(i, this.times.get(1));

                // Add the events to the schedule.
                schedule.addEvent("Sleep Time", "When you sleep", sleep);
                schedule.addEvent("Wake Up Time", "When you wake up", wake);
            }
        }
    }

}
