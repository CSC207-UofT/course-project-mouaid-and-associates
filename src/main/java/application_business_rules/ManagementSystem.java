package application_business_rules;

import java.util.*;

import entities.Medicine;
import entities.Schedule;
import entities.User;
import entities.PrescriptionMedicine;


public class ManagementSystem {
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

    /**
     * Creates a new ManagementSystem instance. Also
     * Creates a new UserManager and ScheduleManager.
     */
    public ManagementSystem(){
        this.userManager = new UserManager();
        this.scheduleManager = new ScheduleManager();
        this.accounts = new HashMap<>();
        this.prescriptionManager = new HashMap<>();
    }
    public Map<String, User> getAccounts(){
        return this.accounts;
    }
    /**
     * Creates a new user instance and stores it in the accounts
     * @param name The name of the user
     * @param username the username that the user will use to log in
     */
    public void createNewUser(String name, String username, String password){
        this.accounts.put(username, this.userManager.addNewUser(name, username, password));
    }

    /**
     * This method sets up the user accounts prior to program starting.
     * @param filename          The file containing user accounts
     * @param readerAndWriter   The data access interface used to read the file.
     */
    public void setUpAccounts(String filename, FileReaderAndWriter readerAndWriter){
        // Here we create the objects from the file.
        Map<String, Object> readObjects = readerAndWriter.read(filename);
        this.accounts = new HashMap<>();

        // If there are no accounts in the file, then we will have an empty hashmap.
        for (String username: readObjects.keySet()){
            this.accounts.put(username, userManager.createNewUser(readObjects.get(username)));
        }
    }

    /**
     * Saves the user accounts to the file with filename.
     * @param filename          The file we are saving user accounts to.
     * @param readerAndWriter   The data access interface used to write into the file.
     */
    public void saveAccounts(String filename, FileReaderAndWriter readerAndWriter){
        //Save the accounts back into the file.
        readerAndWriter.write(filename, accounts);
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
        String username = input[0];
        String password = input[1];
        if (!accounts.containsKey(username)){
            return false;
        } else if (accounts.get(username).getPassword().equals(password)){
            userManager.setUser(accounts.get(username));
            return true;
        }
        else {
            return false;
        }

    }
    /**
     * Gets the info of the user using the username that the user uses
     * @return returns a list that contains the user's username, name and list of medicines (names only).
     */
    public List<String> getUserInfo(){
        List<String> userInfo = new ArrayList<>();
        userInfo.add(userManager.getName());
        userInfo.add(userManager.getUserName());

        // Get specifically the names of the medicine.
        Collections.addAll(userInfo, userManager.getMedicineNames());

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
      
        List<Schedule> scheduleList = userManager.getSchedules();

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
    public void addNewMedicine(String medicineName, int amount, String unitOfMeasurement,
                               String methodOfAdministration, String extraInstructions,
                               List<Map<String, Double>> times) {
        userManager.createMedicine(medicineName, amount, unitOfMeasurement,
                methodOfAdministration, extraInstructions, times);
    }

    public String[] getMedicineInfo(String medName){
        return userManager.getMedicineInfo(medName);
    }

    /**
     * Checks if the ID is in the hashmap
     * @param presName the ID
     * @return Returns True if it is in the hashmap and false otherwise
     */
    public boolean presNameChecker(String presName){
        return this.prescriptionManager.containsKey(presName);
    }

    public void addNewPrescription(List<String> medicines, String presName){
        List<Medicine> allMedicines = userManager.getMedicineEntites();
        List<Medicine> presMedicines = new ArrayList<>();
        for(Medicine medicine : allMedicines){
            if (medicines.contains(medicine.getMedicineName())){
                presMedicines.add(medicine);
            }
        }
        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine(presMedicines, presName);
        prescriptionManager.put(presName, prescriptionMedicine);
    }

    /**
     * Gets a prescription using the given ID
     * @param presName The ID of a specific prescription
     * @return A prescriptionMedicine Object
     */
    public PrescriptionMedicine getPrescription(String presName){
        return prescriptionManager.get(presName);
    }

    /**
     * Returns all the IDS of all the prescriptions
     * @return A set of keys
     */
    public List<String> getPrescriptionsNames(){
        return List.copyOf(prescriptionManager.keySet());
    }
    public void addMedicineToPres(String presName, String medicine){
        List<Medicine> meds = userManager.getMedicineEntites();
        for(Medicine med : meds){
            if(med.getMedicineName().equals(medicine)){
                prescriptionManager.get(presName).addMedicine(med);
            }
        }
    }

    /**
     * Removes a medicine from a given prescription
     * @param presName the name of the prescription
     * @param name the name of the medicine
     */
    public void removeMedicineFromPres(String presName, String name){
        prescriptionManager.get(presName).removeMedicine(name);
    }

    /**
     * Returns the name of all the prescriptions
     * @return a set of prescription names
     */
    public String[] getPrescriptions(){
        return prescriptionManager.keySet().toArray(new String[0]);
    }
    /**
     * Removes a prescription from the user's list of prescriptions
     * @param presName The name of the prescription
     */
    public void removePrescription(String presName) {
        PrescriptionMedicine prescription = prescriptionManager.get(presName);
        String[] medicines = prescription.getPresMedicines();
        userManager.removeMeds(medicines);
        prescriptionManager.remove(presName);
    }
    public void changePrescriptionName(String oldPresName, String newPresName){
        prescriptionManager.get(oldPresName).setPrescriptionName(newPresName);
        PrescriptionMedicine prescription = prescriptionManager.remove(oldPresName);
        prescriptionManager.put(newPresName, prescription);
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
    public void editMedicine(String medName, String[] info, List<Map<String, Double>> times){
        userManager.editMedicine(medName, info);
        scheduleManager.editScheduleTimes(userManager.getMedicineSchedule(medName), times);

        // Change the mapping from the old name to the new name.
        if (!info[0].equals("")){
            userManager.changeMedicineNameInMapping(medName, info[0]);
        }
    }

    /**
     * Sets new Sleep and Wake up times for the User
     * @param times the Sleep and Wakeup times
     */
    public void setSleepAndWakeUpTimes(List<Double> times){
        this.userManager.setActivityTimes(this.userManager.getUser().getSleepClass(), times);
    }
    
    /**
     * Sets new meal times for the User
     * @param times the Meal times
     */
    public void setMealTimes(List<Double> times){
        this.userManager.setActivityTimes(this.userManager.getUser().getMealClass(), times);

    }

}
