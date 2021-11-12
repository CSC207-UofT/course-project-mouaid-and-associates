package application_business_rules;

import java.util.*;

import entities.Medicine;
import entities.MedicineSchedule;
import entities.Schedule;
import entities.User;
import entities.PrescriptionMedicine;

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
    private HashMap<String, PrescriptionMedicine> prescriptionManager;


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
        this.prescriptionManager = new HashMap<>();
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
        List<Medicine> allMedicines = List.copyOf(userManager.getMedicines().values());
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
    public void addMedicineToPres(String presName, Medicine medicine){
        prescriptionManager.get(presName).addMedicine(medicine);
    }

    public void addMedicineToPresByList(String presName, String[] list){
        Medicine medicine = new Medicine(list[0], Integer.parseInt(list[1]), list[2], list[3]);
        prescriptionManager.get(presName).addMedicine(medicine);
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
     * Removes a prescription from the user's list of prescriptions
     * @param presName The name of the prescription
     */
    public void removePrescription(String presName){
        PrescriptionMedicine prescription = prescriptionManager.get(presName);
        String[] medicines = prescription.getPresMedicines();
        userManager.removeMeds(medicines);
        prescriptionManager.remove(presName);
    }

    public void setPrescriptionName(String oldName, String newName){
        PrescriptionMedicine prescription = prescriptionManager.get(oldName);
        prescription.setPrescriptionName(newName);
    }

    public void editPrescription(String presName, String medName, String[] data){
/*        try {*/
            PrescriptionMedicine prescription = prescriptionManager.get(presName);
            String[] medicines = prescription.getPresMedicines();
            Medicine medicine = new Medicine(data[0], Integer.parseInt(data[1]), data[2], data[3]);

            boolean nameInPres = false;
            for (int i = 0; i < prescription.getPresMedicines().length; i++){
                // edits the medicine in prescription
                if(medicines[i].equals(medName)){
                    prescription.getPresMedicinesMedicine().add(i, medicine);
                    nameInPres = true;
                }
            }

            if (!nameInPres){
                //adds the medicine to the prescription
                prescription.addMedicine(medicine);
                List times = new ArrayList();
                for (int i = 4; i<data.length; i++){
                    times.add(data[i]);
                }
                addNewMedicine(data[0], Integer.parseInt(data[1]), data[2], data[3], times);

            }

/*            return "View Acccount Window";
        }
        catch(Exception NullPointerException) {
            System.out.println(presName + "doesn't exist. Try a new prescription name");
            return "Edit Prescription Window";
        }*/
    }

}