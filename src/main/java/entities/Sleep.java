package entities;

import java.time.LocalDateTime;
public class Sleep extends OtherActivities {
    /**
     * This is a Class that stores and Does everything related to Sleeping.
     * Instance Attributes:
     * schedule: This Sleep Class' OtherActivitiesSchedule
     * times: This Sleep Class' sleep and wakeup times
     */

    public Sleep() {
        super();
    }

    /**
     * Creates an OtherActivitiesSchedule for this Sleep Class. The OtherActivitiesSchedule is empty if the class is
     * being instantiated for the first time and is not empty after the user sets Sleep and wake up times
     */
    @Override
    public void createSchedule() {
        this.schedule.removeAllEvents();
        LocalDateTime currentDate = LocalDateTime.now();
        String appendYear;
        String appendDay;
        String appendMonth;
        LocalDateTime appendTo;
        // Checks if the sleep and wake up times isEmpty() which indicates that the SleepClass is being created for
        // the first time and therefore there are no set Sleep and wake up times so the SleepSchedule is empty
        if (!(times.isEmpty())) {
            for (int i = 0; i <= 14; i++) {
                appendTo = currentDate.plusDays(i);
                appendDay = String.valueOf(appendTo.getDayOfMonth());
                appendMonth = String.valueOf(appendTo.getMonthValue());
                appendYear = String.valueOf(appendTo.getYear());
                for (String time : this.times) {
                    if (appendDay.length() == 1) {
                        appendDay = "0" + appendDay;
                    }
                    if (appendMonth.length() == 1) {
                        appendMonth = "0" + appendMonth;
                    }

                    // Add the events to the schedule.
                    schedule.addEvent("Sleep Time", "When you sleep",
                            LocalDateTime.parse(appendYear + "-" + appendMonth + "-" + appendDay + "T" + times.get(0)));
                    schedule.addEvent("Wake Up Time", "When you wake up",
                            LocalDateTime.parse(appendYear + "-" + appendMonth + "-" + appendDay + "T" + times.get(1)));
                }
            }
        }

    }
}