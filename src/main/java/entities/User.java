package entities;

import java.util.HashMap;

public class User {
    /**
     * A class representing the user of the app
     * Instance Attributes:
     * name: THe name of the user.
     * userName: the username the user uses to log in.
     * medicineList: A list of all the medicines used by the user. It is a mapping of a name of a medication to it's
     * instance of Medicine
     *
     * Representation Invariants:
     * - 0 <= events.size()
     */
    private String name;
    private String userName;
    private HashMap<String, Medicine> medicineList;

    public User(String name, String userName){
        this.name = name;
        this.userName = userName;
        this.medicineList = new HashMap<>();
    }

    /**
     * Gets the name of the User.
     * @return The name of the User.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the userName of the User.
     * @return The userName of the User.
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * Gets the medicineList of the User.
     * @return The medicineList of the User.
     */
    public HashMap<String, Medicine> getMedicineList(){
        return this.medicineList;
    }

//    /**
//     * Change the name of the User to newName.
//     * @param newName The new name of the User.
//     */
//    public void changeName(String newName){
//        this.name = newName;
//    }

    /**
     * Add a medicine to this User's medicineList.
     * @param newMedicine The medicine to be added to this User's medicineList.
     */
    public void addMedicine(Medicine newMedicine){
        if (!(this.medicineList.containsKey(newMedicine.getMedicineName()))){
            this.medicineList.put(newMedicine.getMedicineName(), newMedicine);
            newMedicine.setIdNumber(this.medicineList.size() + 1);
        }
    }

    /**
     * Removes the medicine with the specified name, if it exists.
     * @param medName   The name of the medicine to be removed.
     */
    public void removeMedicine(String medName){
        medicineList.remove(medName);
    }

    public Medicine getMedicine(String medName){
        return medicineList.get(medName);
    }

    /**
     * Changes the mapping so that the new name of the medicine maps to the medicine instead of the old name.
     * @param oldName       The old name of the medicine.
     * @param newName       The new name of the medicine.
     */
    public void changeMedicineNameInMapping(String oldName, String newName){
        Medicine med = medicineList.get(oldName);
        removeMedicine(oldName);
        medicineList.put(newName, med);
    }

//    /**
//     * Remove a medicine from this User's medicineList.
//     * @param newMedicine The medicine to be removed from this User's medicineList.
//     */
//    public void removeMedicine(Medicine newMedicine){
//        if (this.medicineList.containsKey(newMedicine)){
//            this.medicineList.remove(newMedicine.getMedicineName(), newMedicine);
//            newMedicine.setIdNumber(0);
//        }
//    }

}

