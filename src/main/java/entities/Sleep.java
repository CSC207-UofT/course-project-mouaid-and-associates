package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sleep {
    /**
     *
     */

    private SleepSchedule sleepSchedule;
    private Double sleepTime;
    private Double wakeUpTime;


    public Sleep(List<Double> times) {
        this.sleepTime = times.get(0);
        this.wakeUpTime = times.get(1);
    }

    public void createSleepSchedule(){
        List<Event> events = new ArrayList<>();
        String[]  daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String i: daysOfWeek){
            Map<String, Double> sleep = new HashMap<>();
            Map<String, Double> wake = new HashMap<>();
            sleep.put(i,this.sleepTime);
            wake.put(i,this.wakeUpTime);
            Event sleepEvent = new Event("Sleep Time", "When you sleep", sleep);
            Event wakeEvent = new Event("Wake Up Time", "When you wake up", wake);
            events.add(sleepEvent);
            events.add(wakeEvent);
        }
        this.sleepSchedule = new SleepSchedule(events);
    }
    public SleepSchedule getMySleepSchedule(){return this.sleepSchedule; }

    public List<Double> getSleepAndWakeUpTime(){
        List<Double> times = new ArrayList<>();
        times.add(this.sleepTime);
        times.add(this.wakeUpTime);
        return times;
    }

    public void setSleepAndWakeUpTime(List<Double> times) {
        this.sleepTime = times.get(0);
        this.wakeUpTime = times.get(1);
        createSleepSchedule();
    }
}
