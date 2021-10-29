package application_business_rules;

import entities.Medicine;
import entities.MedicineSchedule;

import java.util.List;
import java.util.Map;

public class MedicineManager {
    /**
     * A class that manages medicines
     */

    public MedicineManager(){}

    /**
     * Creates a new medicine object
     * @param medicineName The name of the medicine
     * @param amount The amount of this medicine
     * @param methodOfAdministration The method of administration for this medicine
     * @param extraInstructions Extra instructions for this medicine
     * @param times The times to take this medicine
     */
    public Medicine createNewMedicine(String medicineName, int amount,
                                      String methodOfAdministration, String extraInstructions,
                                      List<Map<String, Double>> times){
        Medicine medicine = new Medicine(medicineName, amount, methodOfAdministration, extraInstructions);

        // Add a medicine schedule
        medicine.addMedicineSchedule(times);

        return medicine;
    }

    /**
     * Gets the medicine schedule of this medicine object
     * @param medicine A medicine Object.
     */
    public MedicineSchedule getMedicineSchedule(Medicine medicine){
        return medicine.getMyMedicineSchedule();
    }

    public String[] getMedicineInfo(Medicine medicine){
        return medicine.getMedicineInfo();
    }

//    /**
//     * Gets the medicine's name
//     * @param medicine A medicine Object.
//     */
//    public String getMedicineName(Medicine medicine){
//
//        return medicine.getMedicineName();
//    }
//
//    /**
//     * gets the amount that is in this medicine object
//     * @param medicine A medicine Object.
//     */
//    public int getAmount(Medicine medicine){
//        return medicine.getAmount();
//    }
//
//    /**
//     * gets the method of administration for this medicine object
//     * @param medicine A medicine Object.
//     */
//    public String getMethodOfAdministration(Medicine medicine) {
//
//        return medicine.getMethodOfAdministration();
//    }
//
//    /**
//     * Gets extra intstructions from this medicine object
//     * @param medicine A medicine Object.
//     */
//    public String getExtraInstructions(Medicine medicine) {
//
//        return medicine.getExtraInstructions();
//    }
//
//    /**
//     * Gets the ID number of this medicine Object
//     * @param medicine A medicine Object.
//     */
//    public int getIdNumber(Medicine medicine) {
//
//        return medicine.getIdNumber();
//    }
//
//    /**
//     * Sets a medicine name for this medicine object
//     * @param medicine A medicine Object.
//     * @param medicineName The name of the medicine.
//     */
//    public void setMedicineName(Medicine medicine, String medicineName) {
//
//        medicine.setMedicineName(medicineName);
//    }
//
//    /**
//     * sets an amount for this medicine object
//     * @param medicine A medicine Object.
//     * @param amount The amount that will be set for this medicine object.
//     */
//    public void setAmount(Medicine medicine, int amount) {
//
//        medicine.setAmount(amount);
//    }
//
//    /**
//     * Sets a method of administration for the medicine object
//     * @param medicine A medicine Object.
//     * @param methodOfAdministration Method of administration that will be set for this medicine object.
//     *
//     */
//    public void setMethodOfAdministration(Medicine medicine, String methodOfAdministration) {
//        medicine.setMethodOfAdministration(methodOfAdministration);
//    }
//
//    /**
//     * Sets Extra instructions for the medicine Object
//     * @param medicine A medicine Object.
//     * @param extraInstructions Extra Instructions that will be set for the medicine Object.
//     */
//    public void setExtraInstructions(Medicine medicine, String extraInstructions) {
//        medicine.setExtraInstructions(extraInstructions);
//    }
//
//    /**
//     * Sets new ID number for this medicine Object
//     * @param medicine A medicine Object.
//     * @param idNumber The ID number that will be set for this medicine Object
//     */
//    public void setIdNumber(Medicine medicine, int idNumber) {
//
//        medicine.setIdNumber(idNumber);
//    }
}
