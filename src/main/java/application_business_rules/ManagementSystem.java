package application_business_rules;

import java.util.*;

import entities.Medicine;
import entities.MedicineSchedule;
import entities.Schedule;
import entities.User;

import javax.lang.model.type.NullType;

public class ManagementSystem {
    /**
     * The main manager class that manages all the other manager classes
     * Instance Attributes:
     * UserDatabase: Stores a map of usernames to User entities
     * UserManager: Stores an instance of UserManager class
     * ScheduleManager: Stores an instance of ScheduleManager
     * ScheduleCompiler: Stores an instance ScheduleCompiler
     */
    private HashMap<String, User> userDatabase;
    private UserManager userManager;
    private ScheduleManager scheduleManager;

//    public ManagementSystem(UserManager userManager, ScheduleManager scheduleManager){
//        this.userManager = userManager;
//        this.scheduleManager = scheduleManager;
//    }

    /**
     * Creates a new ManagementSystem instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystem(){
        this.userManager = new UserManager();
        this.scheduleManager = new ScheduleManager();
        this.userDatabase = new HashMap<>();
    }
    public Map<String, User> getDatabase(){
        return this.userDatabase;
    }
    /**
     * Creates a new user instance and stores it in the userDatabase
     * @param name The name of the user
     * @param username the username that the user will use to login
     */
    public void createNewUser(String name, String username){
        this.userDatabase.put(username, this.userManager.addNewUser(name, username));
    }

    // FUTURE TODO: findUser is not required for Phase 0. Needs to be discussed further.

    /**
     * Gets the info of the user using the username that the user uses
     * @return returns a list that contains the user's username, name and list of medicines (names only).
     */
    public List<String> getUserInfo(){
        List<String> userInfo = new ArrayList<>();
        userInfo.add(userManager.getName());
        userInfo.add(userManager.getUserName());

        // Get specifically the names of the medicine.
        userInfo.addAll(List.copyOf(userManager.getMedicines().keySet()));

        return userInfo;
    }

    /**
     * Removes the medicines from the list of medicines the user has.
     * @param medsToRemove      The list of meds to be removed.
     */
    public void removeMedicines(String[] medsToRemove){
        userManager.removeMeds(medsToRemove);
    }

    /**
     * Uses the userManager to get a list of medicines and merges it with the master schedule
     * @return the compiled schedule.
     */
    public String makeSchedule(){
        HashMap<String, Medicine> medicinesDict = userManager.getMedicines();
        List<Medicine> medicineList = new ArrayList<>(medicinesDict.values());
        List<Schedule> scheduleList = new ArrayList<>();
        for (Medicine med: medicineList){

            // Use medicineManager to get the medicine schedule.
            MedicineSchedule medSched = userManager.medicineManager.getMedicineSchedule(med);
            scheduleList.add(medSched);
        }
        return scheduleManager.compileSchedule(scheduleList).toString();
    }

    /** Creates a new instance of Medicine. Takes in the parameters necessary
     * to create the new Medicine.
     *
     * @param medicineName             The name of the medicine.
     * @param amount                   The amount of the medicine.
     * @param methodOfAdministration   How the medicine should be administered (e.g. drink, inject, swallow)
     * @param extraInstructions        Any extra instructions with this medication.
     * @param times                    A list of times to take the medication. Each element is a mapping
     *                                 of a day of the week to an hour. See Event's documentation for more
     *                                 details as the format of day and hour. Each element in the list corresponds
     *                                 to one time stamp. Thus taking the same medication multiple times leads to
     *                                 multiple time stamps, hence the list.
     */
    public void addNewMedicine(String medicineName, int amount,
                               String methodOfAdministration, String extraInstructions,
                               List<Map<String, Double>> times) {
        userManager.createMedicine(medicineName, amount, methodOfAdministration, extraInstructions, times);
    }

}
