package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sleep {
    /**
     * This is a Class that stores the Does everything related to Sleeping.
     * Instance Attributes:
     * sleepSchedule: This Sleep Class' SleepSchedule
     * sleepTime: The sleep time for this Sleep Class
     * wakeUpTime: The wakeup time for this Sleep Class
     */

    private SleepSchedule sleepSchedule;
    private Double sleepTime;
    private Double wakeUpTime;


    public Sleep() {
        this.sleepTime = -1.0;
        this.wakeUpTime = -1.0;
    }

    /**
     * Creates a SleepSchedule for this Sleep Class. The SleepSchedule is empty if the class is being instantiated for
     * the first time and is not empty after the user sets Sleep and wake up times
     */
    public void createSleepSchedule(){
        List<Event> events = new ArrayList<>();
        String[]  daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        this.schedule = new OtherActivitiesSchedule();

        // Checks if the sleepTime and wakUpTime are negative which indicates that the SleepClass is being created for
        // the first time and therefore there are no set Sleep and wake up times so the SleepSchedule is empty
        if (!(sleepTime < 0 || wakeUpTime < 0)) {
            for (String i : daysOfWeek) {
                Map<String, Double> sleep = new HashMap<>();
                Map<String, Double> wake = new HashMap<>();
                sleep.put(i, this.sleepTime);
                wake.put(i, this.wakeUpTime);
                Event sleepEvent = new Event("Sleep Time", "When you sleep", sleep);
                Event wakeEvent = new Event("Wake Up Time", "When you wake up", wake);
                events.add(sleepEvent);
                events.add(wakeEvent);
            }
        }
        this.sleepSchedule = new SleepSchedule(events);
    }

    /**
     * Get the SleepSchedule of this Sleep Class
     * @return This Sleep Class' SleepSchedule
     */
    public SleepSchedule getMySleepSchedule(){
        return this.sleepSchedule;
    }

    /**
     * Set the Sleep and wakeup times of this Sleep Class
     * @param times A list containing the new sleep time and wakeup time
     */
    public void setSleepAndWakeUpTime(List<Double> times) {
        this.sleepTime = times.get(0);
        this.wakeUpTime = times.get(1);
        createSleepSchedule();
    }
}
