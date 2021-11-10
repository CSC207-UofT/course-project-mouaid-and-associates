package application_business_rules;

import entities.Meal;
import entities.OtherActivities;
import entities.Schedule;
import entities.Sleep;

import java.util.List;

public class OtherActivitiesManager {
    /**
     * This class manages the SleepSchedule and the MealSchedule
     */
    public OtherActivitiesManager(){}

    /**
     * Create a default Sleep Class with an empty OtherActivitiesSchedule
     * @return The created Sleep class
     */
    public Sleep createNewSleepClass(){
        Sleep sleepClass = new Sleep();
        sleepClass.createSchedule();
        return sleepClass;
    }

    /**
     * Create a default Meal Class with an empty OtherActivitiesSchedule
     * @return The created Meal class
     */
    public Meal createNewMealClass(){
        Meal mealClass = new Meal();
        mealClass.createSchedule();
        return mealClass;
    }

    /**
     * Get the ActivitySchedule of the given OtherActivities Class
     * @param activity The OtherActivities class to get the Schedule from
     * @return This OtherActivities Class' Schedule
     */
    public Schedule getActivitySchedule(OtherActivities activity) {
        return activity.getMySchedule();
    }

    /**
     * Set the times of the given OtherActivities Class
     * @param activity The OtherActivities class to set the times for
     * @param times A list containing the new times
     */
    public void setActivityTimes(OtherActivities activity, List<Double> times){
        activity.setTime(times);
    }
}
