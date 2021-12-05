package application_business_rules;

import java.time.LocalDateTime;
import java.util.*;

import entities.Schedule;
import entities.User;
import entities.PrescriptionMedicine;


public class ManagementSystemFacade {
    /**
     * The main manager class that manages all the other manager classes
     * Instance Attributes:
     * accounts: Stores a map of usernames to User entities
     * UserManager: Stores an instance of UserManager class
     * ScheduleManager: Stores an instance of ScheduleManager
     * prescriptionManager: A map of Prescription names to PrescriptionMedicine entities.
     */
    private HashMap<String, User> accounts;
    private UserManager userManager;
    private ScheduleManager scheduleManager;
    private HashMap<String, PrescriptionMedicine> prescriptionManager;
    private ManagementSystemAccounts managementSystemAccounts;
    private ManagementSystemActivitySetter managementSystemActivitySetter;
    private ManagementSystemMedicine managementSystemMedicine;
    private ManagementSystemPrescription managementSystemPrescription;

    /**
     * Creates a new ManagementSystemFacade instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystemFacade(){
        this.userManager = new UserManager();
        this.scheduleManager = new ScheduleManager();
        this.accounts = new HashMap<>();
        this.prescriptionManager = new HashMap<>();
        this.managementSystemAccounts = new ManagementSystemAccounts(this.userManager, this.accounts);
        this.managementSystemActivitySetter = new ManagementSystemActivitySetter(this.userManager);
        this.managementSystemMedicine = new ManagementSystemMedicine(this.userManager, this.scheduleManager);
        this.managementSystemPrescription = new ManagementSystemPrescription(this.userManager,
                this.prescriptionManager);
    }
    public Map<String, User> getAccounts(){
        return this.managementSystemAccounts.getAccounts();
    }
    /**
     * Creates a new user instance and stores it in the accounts
     * @param name The name of the user
     * @param username the username that the user will use to log in
     */
    public void createNewUser(String name, String username, String password){
        this.managementSystemAccounts.createNewUser(name, username, password);
    }

    /**
     * This method sets up the user accounts prior to program starting.
     * @param filename          The file containing user accounts
     * @param readerAndWriter   The data access interface used to read the file.
     */
    public void setUpAccounts(String filename, FileReaderAndWriter readerAndWriter){
        this.managementSystemAccounts.setUpAccounts(filename, readerAndWriter);
    }


    /**
     * Saves the user accounts to the file with filename.
     * @param filename          The file we are saving user accounts to.
     * @param readerAndWriter   The data access interface used to write into the file.
     */
    public void saveAccounts(String filename, FileReaderAndWriter readerAndWriter){
        this.managementSystemAccounts.saveAccounts(filename, readerAndWriter);
    }

    /**
     *
     * @param input The input that the user gave
     * @return Whether or not the input the user gave is for an account
     *
     * Preconditions:
     *  - length(input) == 2
     */
    public boolean verifyUserAccount(String[] input){
        return this.managementSystemAccounts.verifyUserAccount(input);
    }

    /**
     * Gets the info of the user using the username that the user uses
     * @return returns a list that contains the user's username, name and list of medicines (names only).
     */
    public List<String> getUserInfo(){
        return this.managementSystemAccounts.getUserInfo();
    }

    /**
     * Removes the medicines from the list of medicines the user has.
     * @param medsToRemove      The list of meds to be removed.
     */
    public void removeMedicines(String[] medsToRemove){
        this.managementSystemMedicine.removeMedicines(medsToRemove);
    }

    /**
     * Uses the userManager to get a list of medicines and merges it with the master schedule
     * @return the compiled schedule.
     */
    public String[] makeSchedule(){
      
        List<Schedule> scheduleList = userManager.getSchedules();

        return scheduleManager.compileSchedule(scheduleList).convertToString();
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
    public void addNewMedicine(String medicineName, int amount, String unitOfMeasurement,
                               String methodOfAdministration, String extraInstructions,
                               List<LocalDateTime> times) {
        this.managementSystemMedicine.addNewMedicine(medicineName, amount, unitOfMeasurement,
                methodOfAdministration, extraInstructions, times);
    }

    public String[] getMedicineInfo(String medName){return this.managementSystemMedicine.getMedicineInfo(medName);}

    /**
     * Checks if the ID is in the hashmap
     * @param presName the ID
     * @return Returns True if it is in the hashmap and false otherwise
     */
    public boolean presNameChecker(String presName){
        return this.managementSystemPrescription.presNameChecker(presName);
    }

    public void addNewPrescription(List<String> medicines, String presName){
       this.managementSystemPrescription.addNewPrescription(medicines, presName);
    }

    /**
     * Gets a prescription using the given ID
     * @param presName The ID of a specific prescription
     * @return A prescriptionMedicine Object
     */
    public PrescriptionMedicine getPrescription(String presName){
        return this.managementSystemPrescription.getPrescription(presName);
    }

    /**
     * Returns all the IDS of all the prescriptions
     * @return A set of keys
     */
    public List<String> getPrescriptionsNames(){
        return this.managementSystemPrescription.getPrescriptionsNames();
    }
    public void addMedicineToPres(String presName, String medicine){
        this.managementSystemPrescription.addMedicineToPres(presName, medicine);
    }

    /**
     * Removes a medicine from a given prescription
     * @param presName the name of the prescription
     * @param name the name of the medicine
     */
    public void removeMedicineFromPres(String presName, String name){
        this.managementSystemPrescription.removeMedicineFromPres(presName, name);
    }

    /**
     * Returns the name of all the prescriptions
     * @return a set of prescription names
     */
    public String[] getPrescriptions(){
        return this.managementSystemPrescription.getPrescriptions();
    }
    /**
     * Removes a prescription from the user's list of prescriptions
     * @param presName The name of the prescription
     */
    public void removePrescription(String presName) {
        this.managementSystemPrescription.removePrescription(presName);
    }
    public void changePrescriptionName(String oldPresName, String newPresName){
        this.managementSystemPrescription.changePrescriptionName(oldPresName, newPresName);
    }

    /**
     * Edits a medicine using the given info. The first element is the new name of the medicine.
     * The second element is the new unit of measurement, the third element is the new method of administration,
     * the fourth element is the new amount, and the fifth element is the new extra instructions.
     *
     * The list of mappings called times is for the new times to take the medicine.
     *
     * @param info      The info used to edit the medicine. The first element is the medicine name.
     * @param times     The new times to take this medicine.
     */
    public void editMedicine(String medName, String[] info, List<LocalDateTime> times){
        this.managementSystemMedicine.editMedicine(medName, info, times);
    }

    /**
     * Sets new Sleep and Wake up times for the User
     * @param times the Sleep and Wakeup times
     */
    public void setSleepAndWakeUpTimes(List<String> times){
        this.managementSystemActivitySetter.setSleepAndWakeUpTimes(times);
    }
    
    /**
     * Sets new meal times for the User
     * @param times the Meal times
     */
    public void setMealTimes(List<String> times){
        this.managementSystemActivitySetter.setMealTimes(times);

    }

}
