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

    public ManagementSystemPrescription(UserManager userManager,
                                        HashMap<String, PrescriptionMedicine> prescriptionManager){
        this.userManager = userManager;
    }
    /**
     * Checks if the ID is in the hashmap
     * @param presName the ID
     * @return Returns True if it is in the hashmap and false otherwise
     */
    public boolean presNameChecker(String presName){
        return userManager.getPrescriptionNames().contains(presName);
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
        userManager.addPrescription(presName, prescriptionMedicine);
    }

    /**
     * Gets a prescription using the given ID
     * @param presName The ID of a specific prescription
     * @return A prescriptionMedicine Object
     */
    public PrescriptionMedicine getPrescription(String presName){
        return userManager.getPrescription(presName);
    }

    /**
     * Returns all the IDS of all the prescriptions
     * @return A set of keys
     */
    public List<String> getPrescriptionsNames(){
        return List.copyOf(userManager.getPrescriptionNames());
    }
    public void addMedicineToPres(String presName, String medicine){
        List<Medicine> meds = userManager.getMedicineEntites();
        for(Medicine med : meds){
            if(med.getMedicineName().equals(medicine)){
                userManager.getPrescriptionEntity(presName).addMedicine(med);
            }
        }
    }

    /**
     * Removes a medicine from a given prescription
     * @param presName the name of the prescription
     * @param name the name of the medicine
     */
    public void removeMedicineFromPres(String presName, String name){
        userManager.getPrescriptionEntity(presName).removeMedicine(name);
    }

    /**
     * Returns the name of all the prescriptions
     * @return a set of prescription names
     */
    public String[] getPrescriptions(){
        return userManager.getPrescriptionNames().toArray(new String[0]);
    }
    /**
     * Removes a prescription from the user's list of prescriptions
     * @param presName The name of the prescription
     */
    public void removePrescription(String presName) {
        PrescriptionMedicine prescription = userManager.getPrescription(presName);
        String[] medicines = prescription.getPresMedicines();
        userManager.removeMeds(medicines);
        userManager.removePrescription(presName);
    }
    public void changePrescriptionName(String oldPresName, String newPresName){
        userManager.getPrescription(oldPresName).setPrescriptionName(newPresName);
        PrescriptionMedicine prescription = userManager.getPrescriptionEntity(oldPresName);
        userManager.removePrescription(oldPresName);
        userManager.addPrescription(newPresName, prescription);

    }
}
