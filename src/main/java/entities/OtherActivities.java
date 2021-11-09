package entities;

import java.util.ArrayList;
import java.util.List;

public abstract class OtherActivities {
    /**
     * The Super Class for other events in the schedule like sleep times and meal times
     */
    private List<Double> times;
    private Schedule schedule;

    public OtherActivities(){
        this.times = new ArrayList<>();
    }

    abstract void createSchedule();

    /**
     * Get the Schedule of this Activity Class
     * @return This Activity Class' respective Schedule
     */
    public Schedule getMySchedule(){
        return this.schedule;
    }

    /**
     * Set the activity times of this Activity Class
     * @param times A list containing the new activity times
     */
    public void setTime(List<Double> times) {
        this.times = times;
        createSchedule();
    }

}
