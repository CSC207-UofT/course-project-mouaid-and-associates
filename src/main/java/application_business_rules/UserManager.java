package application_business_rules;

import entities.*;

import java.util.*;

public class UserManager {
    /**
     * A class that manages everything about the User Entity
     * Instance Attributes:
     * user: the user object
     * medicineManager: MedicineManager Object that we will use to manage the user's medicines
     * Representation Invariants:
     *  - user is a User Object
     *  - medicineManager is a MedicineManager Object
     *
     */
    private User user;
    public MedicineManager medicineManager;
    public OtherActivitiesManager otherActivitiesManager;

    /**
     * Creates a new instance of UserManager, with no user defined yet.
     * A user needs to added using addNewUser().
     */
    public UserManager(){
        this.medicineManager = new MedicineManager();
        this.otherActivitiesManager = new OtherActivitiesManager();
    }

    /**
     * Returns a new user entity (Object). Also stores this instance.
     * @param name The name of the user.
     * @param username the username of the account
     */
    public User addNewUser(String name,String username, String password){
        this.user = new User(name, username, password);
        createUserSleepClass();// Create a default Sleep Class with an empty Schedule
        createUserMealClass();// Create a default Meal Class with an empty Schedule
        return this.user;
    }

    /**
     * Returns a new user object by casting the passed in object.
     *
     * Preconditions:
     * - newUser is an instance of User
     *
     * @param newUser   The new user.
     * @return          The new user, as a User object.
     */
    public User createNewUser(Object newUser){
        if (newUser instanceof User){
            return (User) newUser;
        }

        // Should never reach this line based on the Precondition.
        return null;
    }

    /**
     * Creates a new medicine entity (Object). Adds the medicine to User's list of medicine.
     * @param medicineName The name of the medicine
     * @param amount The amount of this medicine
     * @param methodOfAdministration The method of administration for this medicine
     * @param extraInstructions Extra instructions for this medicine
     * @param times The times to take this medication.
     */
    public void createMedicine(String medicineName, int amount, String unitOfMeasurement,
                               String methodOfAdministration, String extraInstructions,
                               List<Map<String, Double>> times){

        user.addMedicine(this.medicineManager.createNewMedicine(medicineName, amount, unitOfMeasurement,
                methodOfAdministration, extraInstructions, times));
    }

    /**
     * Removes the given medicines from the list of medicines the user has.
     * @param medsToRemove  The list of medicines to be removed.
     */
    public void removeMeds(String[] medsToRemove){
        for (String medName: medsToRemove){
            user.removeMedicine(medName);
        }
    }

    /**
     * Gets the user that is managed by UserManger
     * @return The user that is managed by UserManger
     */
    public User getUser(){
        return this.user;
    }

    /**
     * Gets the name of the user that is managed by UserManger
     * @return The name of the user that is managed by UserManger
     */
    public String getName(){
        return this.user.getName();
    }

    /**
     * Gets the userName of the user that is managed by UserManger
     * @return The userName of the user that is managed by UserManger
     */
    public String getUserName(){
        return this.user.getUserName();
    }

    /**
     * Gets the medicine schedules for every medicine associated with this user.
     * @return All the medicine schedules associated with this user.
     */
    public List<Schedule> getMedicineSchedules(){
        List<Schedule> schedules = new ArrayList<>();
        for (Medicine med: user.getMedicineList().values()){
            schedules.add(medicineManager.getMedicineSchedule(med));
        }
        return schedules;
    }

    /**
     * @return The names of the medicines that are associated with this user.
     */
    public String[] getMedicineNames(){
        return user.getMedicineList().keySet().toArray(new String[0]);
    }

    public List<Medicine> getMedicineEntites(){
        return new ArrayList<>(user.getMedicineList().values());
    }
    public String[] getMedicineInfo(String medName){
        Medicine medicine = user.getMedicine(medName);
        return medicineManager.getMedicineInfo(medicine);
    }

    /**
     * Sets the attributes of the medicine with the given medName, using the information in info.
     * @param medName       The name of the medicine that will be changed.
     * @param info          The new information that will replace the old information.
     */
    public void editMedicine(String medName, String[] info){
        // Get the medicine to be edited.
        Medicine med = user.getMedicine(medName);

        // First create a mapping that maps a variable/attribute name to a value in the list.
        // The order is pre-determined.
        Map<String, String> newInfo = new HashMap<>();
        newInfo.put("name", info[0]);
        newInfo.put("unit of measurement", info[1]);
        newInfo.put("method of administration", info[2]);
        newInfo.put("amount", info[3]);
        newInfo.put("extra instructions", info[4]);

        // Call medicine manager to edit the medicine.
        medicineManager.editMedicine(med, newInfo);
    }

    /**
     * Sets the User for this UserManager.
     * @param user  The new User.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Changes the mapping so that newName refers to the medicine object instead of oldName.
     * @param oldName       The old name of the medicine.
     * @param newName       The new name of the medicine.
     */
    public void changeMedicineNameInMapping(String oldName, String newName){
        user.changeMedicineNameInMapping(oldName, newName);
    }

    /**
     * Returns the medicine schedule of the medicine with the given name.
     * @param medName       The name of the medicine.
     * @return              The medicine's schedule.
     */
    public Schedule getMedicineSchedule(String medName){
        Medicine med = user.getMedicine(medName);
        return medicineManager.getMedicineSchedule(med);
    }

    /**
     * Returns all the schedules related to this User.
     * @return  A list of schedules associated with the user.
     */
    public List<Schedule> getSchedules(){
        // Initialize a schedule list, and get the medicine schedules.
        List<Schedule> schedules = getMedicineSchedules();
        // Gets the sleep schedule
        schedules.add(getActivitySchedule(this.user.getSleepClass()));
        schedules.add(getActivitySchedule(this.user.getMealClass()));

        return schedules;
    }

    /**
     * Create a new Sleep Class and assign it to the User's sleepClass
     */
    public void createUserSleepClass(){
        Sleep sleep = this.otherActivitiesManager.createNewSleepClass();
        this.user.setSleepClass(sleep);
    }

    /**
     * Create a new Meal Class and assign it to the User's mealClass
     */
    public void createUserMealClass(){
        Meal meal = this.otherActivitiesManager.createNewMealClass();
        this.user.setMealClass(meal);
    }


    /**
     * Set the Activity times of the User's given OtherActivities Class
     * @param times A list containing the new activity time
     */
    public void setActivityTimes(OtherActivities activities, List<Double> times){
        this.otherActivitiesManager.setActivityTimes(activities, times);
    }

    /**
     * Get the Activity Schedule of the given User's Activity
     * @return The User's ActivitySchedule for the given Activity
     */
    public Schedule getActivitySchedule(OtherActivities activity){
        return this.otherActivitiesManager.getActivitySchedule(activity);
    }
}
