package interface_adapters;

import application_business_rules.ManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppManagerActivitySetter {
    /**
     * The class that takes care of setting the activity times in the AppManagerFacade
     */

    Map<String, Window> windows;
    ManagementSystem managementSystem;

    public AppManagerActivitySetter(Map<String, Window> windows, ManagementSystem managementSystem) {
        this.windows = windows;
        this.managementSystem = managementSystem;
    }

    /**
     * Shows the set sleep times window, and allows the user to set their sleep schedule.
     * @return  The name of the next window to open.
     */
    public String setSleepTimes(){
        // Call SetSleepTimingsWindow to display the fields to enter the sleep and wakeup times
        Window setSleepTimingsWindow = windows.get("Set Sleep Timings Window");

        String[] stringTimings = setSleepTimingsWindow.getUserInput();

        String sleepTime = stringTimings[0];
        String wakeUpTime = stringTimings[1];

        List<String> times = new ArrayList<>();
        times.add(sleepTime);
        times.add(wakeUpTime);

        this.managementSystem.setSleepAndWakeUpTimes(times);

        // Return to the account page.
        return "View Account Window";
    }

    /**
     * Shows the set meal times window, and allows the user to set their meal schedule.
     * @return  The name of the next window to open.
     */
    public String setMealTimes(){
        // Call SetMealTimingsWindow to display the fields to enter the Meal times
        Window setMealTimingsWindow = windows.get("Set Meal Timings Window");

        String[] stringTimings = setMealTimingsWindow.getUserInput();
        List<String> times = new ArrayList<>();

        for (String timings: stringTimings){
            times.add(timings);
        }

        this.managementSystem.setMealTimes(times);

        // Return to the account page.
        return "View Account Window";
    }
}
