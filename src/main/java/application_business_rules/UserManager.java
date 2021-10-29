package application_business_rules;

import entities.Medicine;
import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * Gets the medicineList of the user that is managed by UserManger
     * @return The medicineList of the user that is managed by UserManger
     */
    public HashMap<String, Medicine> getMedicines(){
        return this.user.getMedicineList();
    }

    public String[] getMedicineInfo(String medName){
        Medicine medicine = user.getMedicine(medName);
        return medicineManager.getMedicineInfo(medicine);
    }
}
