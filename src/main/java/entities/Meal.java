package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Meal extends OtherActivities{
    /**
     *
     */

    private Schedule schedule;
    private List<Double> times;


    public Meal() {
        super();
        this.times = new ArrayList<>();
    }

    public void createSchedule() {
        String[]  daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        this.schedule = new OtherActivitiesSchedule();

        // Checks if the meal times isEmpty() which indicates that the MealClass is being created for
        // the first time and therefore there are no set meal times so the ActivitiesSchedule is empty
        if (!(times.isEmpty())) {
            for (String i : daysOfWeek) {
                for (Double time : this.times) {
                    Map<String, Double> meal = new HashMap<>();
                    meal.put(i, time);

                    // Add the events to the schedule.
                    schedule.addEvent("Meal Time", "When you have a meal", meal);
                }
            }
        }
    }

}
