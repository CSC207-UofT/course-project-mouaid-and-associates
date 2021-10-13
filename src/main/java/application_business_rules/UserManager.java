package application_business_rules;

import entities.Medicine;
import entities.User;

import java.util.Dictionary;
import java.util.List;

public class UserManager {
    /**
     * Instance Attributes:
     * user: the user object
     * medicineManager: MedicineManager Object that we will use to manage the user's medicines
     * Representation Invariants:
     *  - user is a User Object
     *  - medicineManager is a MedicineManager Object
     *
     */
    private User user;
    private MedicineManager medicineManager;

    public UserManager(User user, MedicineManager medicineManager){
        this.user = user;
        this.medicineManager = medicineManager;
    }

    /**
     * Returns a new user entity (Object)
     * @param name The name of the user.
     * @param username the username of the account
     */
    public User addNewUser(String name,String username){
        return new User(name, username);
    }
    /**
     * Returns a new medicine entity (Object)
     * @param medicineName The name of the medicine
     * @param amount The amount of this medicine
     * @param methodOfAdministration The method of administration for this medicine
     * @param extraInstructions Extra instructions for this medicine
     * @param times The times to take this medication.
     */
    public Medicine CreateMedicine(String medicineName, int amount,
                                   String methodOfAdministration, String extraInstructions,
                                   List<Dictionary<String, Double>> times){
        return this.medicineManager.createNewMedicine(medicineName, amount, methodOfAdministration, extraInstructions,
                times);
    }
}
