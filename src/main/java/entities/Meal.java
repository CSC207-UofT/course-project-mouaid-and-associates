package entities;

import java.time.LocalDateTime;

public class Meal extends OtherActivities{
    /**
     * This is a Class that stores and Does everything related to Meal times.
     * Instance Attributes:
     * schedule: This Meal Class' OtherActivitiesSchedule
     * times: This Meal Class' sleep and wakeup times
     */



    public Meal() {
        super();
    }


    /**
     * Creates an OtherActivitiesSchedule for this Meal Class. The OtherActivitiesSchedule is empty if the class is
     * being instantiated for the first time and is not empty after the user sets Meal times
     */
    @Override
    public void createSchedule() {
        this.schedule.removeAllEvents();
        LocalDateTime currentDate = LocalDateTime.now();
        String appendYear;
        String appendDay;
        String appendMonth;
        LocalDateTime appendTo;
        // Checks if the meal times isEmpty() which indicates that the MealClass is being created for
        // the first time and therefore there are no set meal times so the ActivitiesSchedule is empty
        if (!(times.isEmpty())) {
            for (int i = 0; i < 7; i++) {
                appendTo = currentDate.plusDays(i);
                appendDay = String.valueOf(appendTo.getDayOfMonth());
                appendMonth = String.valueOf(appendTo.getMonthValue());
                appendYear = String.valueOf(appendTo.getYear());
                for (String time : this.times) {
                if (appendDay.length() == 1){
                    appendDay = "0" + appendDay;
                }
                if (appendMonth.length() == 1){
                    appendMonth = "0" + appendMonth;
                }

                    // Add the events to the schedule.
                    schedule.addEvent("Meal Time", "Time to eat",
                            LocalDateTime.parse(appendYear+ "-" + appendMonth + "-" + appendDay + "T" + time));
                }
            }
        }
    }

}
