package application_business_rules;

import entities.Medicine;
import entities.Schedule;
import entities.User;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;

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

//    public UserManager(User user){
//        this.user = user;
//        this.medicineManager = new MedicineManager();
//    }

    /**
     * Creates a new instance of UserManager, with no user defined yet.
     * A user needs to added using addNewUser().
     */
    public UserManager(){
        this.medicineManager = new MedicineManager();
    }

    /**
     * Returns a new user entity (Object). Also stores this instance.
     * @param name The name of the user.
     * @param username the username of the account
     */
    public User addNewUser(String name,String username){
        this.user = new User(name, username);
        return this.user;
    }

    /**
     * Creates a new medicine entity (Object). Adds the medicine to User's list of medicine.
     * @param medicineName The name of the medicine
     * @param amount The amount of this medicine
     * @param methodOfAdministration The method of administration for this medicine
     * @param extraInstructions Extra instructions for this medicine
     * @param times The times to take this medication.
     */
    public void createMedicine(String medicineName, int amount,
                                   String methodOfAdministration, String extraInstructions,
                                   List<Map<String, Double>> times){

        user.addMedicine(this.medicineManager.createNewMedicine(medicineName, amount,
                methodOfAdministration, extraInstructions,
                times));
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
        newInfo.put("method of administration", info[1]);
        newInfo.put("amount", info[2]);
        newInfo.put("extra instructions", info[3]);

        // Change the mapping in the user's list of medicines.

        // Call medicine manager to edit the medicine.
        medicineManager.editMedicine(med, newInfo);
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
}
