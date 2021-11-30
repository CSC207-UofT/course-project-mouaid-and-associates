package application_business_rules;

import java.util.List;

public class ManagementSystemActivitySetter {
    /**
     * The class that takes care of all Activity related tasks in the ManagementSystemFacade
     */

    private UserManager userManager;

    /**
     * Creates a new ManagementSystemFacade instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystemActivitySetter(UserManager userManager){
        this.userManager = userManager;
    }

    /**
     * Sets new Sleep and Wake up times for the User
     * @param times the Sleep and Wakeup times
     */
    public void setSleepAndWakeUpTimes(List<String> times){
        this.userManager.setActivityTimes(this.userManager.getUser().getSleepClass(), times);
    }

    /**
     * Sets new meal times for the User
     * @param times the Meal times
     */
    public void setMealTimes(List<String> times){
        this.userManager.setActivityTimes(this.userManager.getUser().getMealClass(), times);

    }
}
