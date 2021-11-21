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
    public Medicine createNewMedicine(String medicineName, int amount, String unitOfMeasurement,
                                      String methodOfAdministration, String extraInstructions,
                                      List<Map<String, Double>> times){
        Medicine medicine = new Medicine(medicineName, amount, unitOfMeasurement,
                methodOfAdministration, extraInstructions);

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

    /**
     * Sets the attributes for the Medicine passed in based on the values in the newInfo mapping.
     * @param med       The Medicine object that we will change the attributes off.
     * @param newInfo   The mapping that contains the new information used to set the attributes of the Medicine.
     */
    public void editMedicine(Medicine med, Map<String, String> newInfo){
        boolean makeNewDescription = false;     // for when the description needs to be updated
        String newEventDescription;

        if(!newInfo.get("name").equals("")){
            med.setMedicineName(newInfo.get("name"));
            makeNewDescription = true;      // set to true since we changed part of the description
        }
        if(!newInfo.get("method of administration").equals("")){
            med.setMethodOfAdministration(newInfo.get("method of administration"));
            makeNewDescription = true;
        }
        if(!newInfo.get("extra instructions").equals("")){
            med.setExtraInstructions(newInfo.get("extra instructions"));
            makeNewDescription = true;
        }
        if(!newInfo.get("unit of measurement").equals("")){
            med.setUnitOfMeasurement(newInfo.get("unit of measurement"));
            makeNewDescription = true;
        }
        if(!newInfo.get("amount").equals("")){
            med.setAmount(Integer.parseInt(newInfo.get("amount")));
            makeNewDescription = true;
        }

        // If we have to make a new description, then we make the description based on the new values
        // of med.
        if (makeNewDescription){
            newEventDescription = med.makeDescription();
            // Change the descriptions of the associated medicine schedule.
            med.getMyMedicineSchedule().setEventDescriptions(newEventDescription);
        }

    }
}
