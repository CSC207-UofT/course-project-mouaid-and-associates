package application_business_rules;

import entities.Medicine;
import entities.PrescriptionMedicine;
import entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ManagementSystemPrescription {
    /**
     * The class that takes care of all prescription related tasks in the ManagementSystemFacade
     */

    private UserManager userManager;
    private HashMap<String, PrescriptionMedicine> prescriptionManager;

    public ManagementSystemPrescription(UserManager userManager,
                                        HashMap<String, PrescriptionMedicine> prescriptionManager){
        this.userManager = userManager;
        this.prescriptionManager = prescriptionManager;
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
}
