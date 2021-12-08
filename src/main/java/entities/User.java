package entities;

import java.io.Serializable;
import java.util.HashMap;

public class User implements Serializable {
    /**
     * A class representing the user of the app
     * Instance Attributes:
     * name: THe name of the user.
     * userName: the username the user uses to log in.
     * password: The password required for login
     * medicineList: A list of all the medicines used by the user. It is a mapping of a name of a medication to it's
     * instance of Medicine
     *
     * Representation Invariants:
     * - 0 <= events.size()
     */
    private String name;
    private String userName;
    private String password;
    private HashMap<String, Medicine> medicineList;
    private HashMap<String, PrescriptionMedicine> prescriptionList;
    private Sleep sleepClass;
    private Meal mealClass;

    public User(String name, String userName, String password){
        this.name = name;
        this.userName = userName;
        this.medicineList = new HashMap<>();
        this.password = password;
        this.prescriptionList = new HashMap<>();
    }

    /**
     * Gets the name of the User.
     * @return The name of the User.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the user's password
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the userName of the User.
     * @return The userName of the User.
     */
    public String getUserName(){
        return this.userName;
    }

    /**
     * Gets prescription List
     * @return prescriptions
     */
    public HashMap<String, PrescriptionMedicine> getPrescriptionList() {
        return prescriptionList;
    }

    /**
     * Adds a new prescriptio
     * @param name name of the prescription
     * @param prescription the prescription entity
     */
    public void addPrescription(String name, PrescriptionMedicine prescription){
        prescriptionList.put(name, prescription);
    }

    /**
     * removes a prescription
     * @param name name of prescription
     */
    public void removePrescription(String name){
        prescriptionList.remove(name);
    }

    /**
     * gets prescription entity
     * @param name name of the prescription
     * @return the prescription identity
     */
    public PrescriptionMedicine getPrescription(String name){
        return prescriptionList.get(name);
    }
    /**
     * Gets the medicineList of the User.
     * @return The medicineList of the User.
     */
    public HashMap<String, Medicine> getMedicineList(){
        return this.medicineList;
    }

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
     *
     * @param oldName       The old name of the medicine.
     * @param newName       The new name of the medicine.
     */
    public void changeMedicineNameInMapping(String oldName, String newName){
        Medicine med = medicineList.get(oldName);
        removeMedicine(oldName);
        medicineList.put(newName, med);
    }

    /**
     * Set the SleepClass instance with a Sleep Class
     * @param sleep the Sleep Class to be set
     */
    public void setSleepClass(Sleep sleep){
        this.sleepClass = sleep;
    }

    /**
     * Get the Sleep Class of this User class
     * @return This User's Sleep Class
     */
    public Sleep getSleepClass() {
        return this.sleepClass;
    }

    public void setMealClass(Meal meal){
        this.mealClass = meal;
    }

    public Meal getMealClass(){ return this.mealClass;}

}

