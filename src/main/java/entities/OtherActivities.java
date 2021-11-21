package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class OtherActivities implements Serializable {
    /**
     * The Super Class for other events in the schedule like sleep times and meal times
     */
    protected List<String> times;
    protected Schedule schedule;

    public OtherActivities(){
        this.schedule = new Schedule();
        this.times = new ArrayList<>();
    }

    /**
     * An abstract class for creating a schedule for the give activity
     */
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
    public void setTime(List<String> times) {
        this.times = times;
        createSchedule();
    }

}
