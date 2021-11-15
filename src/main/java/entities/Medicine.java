package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Medicine implements Serializable {
    /**
     * A class containing a singular medicine in this app
     * Instance Attributes:
     * - medicineName: The name of the medicine corresponding to this Medicine class
     * - amount: The amount of medication available which is dependent on the method of administration
     * - methodOfAdministration: The method of administration of the medicine, like drink, swallow, injection, etc.
     * - extraInstructions: Any extra instructions associated with the administration of this medicine, like if it
     *                      should be taken with food, etc.
     * - idNumber: A unique ID number to identify this instance of medicine in the User's medicine list.
     * - myMedicineSchedule: An instance of medicineSchedule for the medicine corresponding to this Medicine class
     *
     * Representation Invariants:
     * - amount == -1 when there is no amount associated with this medicine.
     *
     */
    private String medicineName;
    private String unitOfMeasurement;
    private int amount;
    private String methodOfAdministration;
    private String extraInstructions;
    private int idNumber;
    private MedicineSchedule myMedicineSchedule;

    public Medicine(String medicineName, int amount, String unitOfMeasurement, String methodOfAdministration,
                    String extraInstructions) {
        this.medicineName = medicineName;
        this.amount = amount;
        this.methodOfAdministration = methodOfAdministration;
        this.extraInstructions = extraInstructions;
        this.idNumber = 0;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /**
     * Adds a new MedicineSchedule to this Medicine object.
     * @param times The times to take this medication.
     */
    public void addMedicineSchedule(List<Map<String, Double>> times){
        // Make an event
        String description;
        description = makeDescription();


        List<Event> events = new ArrayList<>();

        // Make a schedule
        this.myMedicineSchedule = new MedicineSchedule(medicineName, events);

        // Iterate through the list of times
        for(Map<String, Double> time : times){
            // Add an event to the list of events in the schedule.
            myMedicineSchedule.addEvent(medicineName, description, time);
        }


    }

    /**
     * Creates a new description for the events in this medicine's schedule, based
     * on the attributes of this Medicine.
     * @return      A string representing the event description of this medicine.
     */
    public String makeDescription() {
        String description;
        if (amount <= 0){
            description = methodOfAdministration + " " +
                    medicineName + ". " +
                    extraInstructions;
        } else {
            description = methodOfAdministration + " " + amount + " " +
                    medicineName + " " +
                    unitOfMeasurement + ". " +
                    extraInstructions;
        }
        return description;
    }

    /**
     * Gets the medicineSchedule in this Medicine Class
     * @return  The medicineSchedule in this Medicine Class
     */
    public MedicineSchedule getMyMedicineSchedule() {
        return myMedicineSchedule;
    }

    /**
     * Gets the medicineName in this Medicine Class
     * @return  The medicineName in this Medicine Class
     */
    public String getMedicineName(){
        return this.medicineName;
    }

    /**
     * Sets the medicineName in this Medicine Class
     * @param medicineName The medicineName to be set to this Medicine Class
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
        myMedicineSchedule.setMedicineName(medicineName);
        myMedicineSchedule.setEventNames(medicineName);
    }

    /**
     * Sets the amount in this Medicine Class
     * @param amount The medicineName to be set to this Medicine Class
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Sets the methodOfAdministration in this Medicine Class
     * @param methodOfAdministration The medicineName to be set to this Medicine Class
     */
    public void setMethodOfAdministration(String methodOfAdministration) {
        this.methodOfAdministration = methodOfAdministration;
    }

    /**
     * Sets the extraInstructions in this Medicine Class
     * @param extraInstructions The medicineName to be set to this Medicine Class
     */
    public void setExtraInstructions(String extraInstructions) {
        this.extraInstructions = extraInstructions;
    }

    /**
     * Sets the idNumber in this Medicine Class
     * @param idNumber The medicineName to be set to this Medicine Class
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Sets the unit of measurement of this medicine
     * @param unitOfMeasurement     The new unit of measurement.
     */
    public void setUnitOfMeasurement(String unitOfMeasurement){
        this.unitOfMeasurement = unitOfMeasurement;
    }

    /**
     * Returns the information about this medicine in a formatted string array. The elements are as
     * follows: medicine name, unit used to measure dosage, dosage amount, method of administration, extra
     * instructions, and times to take the medication.
     *
     * @return  A formatted string array of the medicine in this Medicine. The order is as listed in the
     *          documentation.
     */
    public String[] getMedicineInfo(){
        String[] info = new String[6 + myMedicineSchedule.getNumberOfEvents()];
        String[] listOfMedicineTimes;
        info[0] = "Name of Medicine: " + medicineName;
        info[1] = "How the dosage is measured: " + unitOfMeasurement;
        info[2] = "Dosage amount: ";

        // We will return the amount if there is a value associated with it.
        if (amount >= 0){
            info[2] = info[2] + amount;
        }

        info[3] = "Method of Administration: " + methodOfAdministration;
        info[4] = "Extra Instructions: " + extraInstructions;
        info[5] = "Times: ";
        listOfMedicineTimes = myMedicineSchedule.getEventTimes();

        // Format the times:
        for (int i = 6; i < info.length; i++){
            info[i] = "   - " + listOfMedicineTimes[i - 6];
        }

        return info;
    }
}
