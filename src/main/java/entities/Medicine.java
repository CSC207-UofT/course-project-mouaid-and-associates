package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Medicine {
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
    private int amount;
    private String methodOfAdministration;
    private String extraInstructions;
    private int idNumber;
    private MedicineSchedule myMedicineSchedule;

    public Medicine(String medicineName, int amount, String methodOfAdministration, String extraInstructions) {
        this.medicineName = medicineName;
        this.amount = amount;
        this.methodOfAdministration = methodOfAdministration;
        this.extraInstructions = extraInstructions;
        this.idNumber = 0;
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
        this.myMedicineSchedule = new MedicineSchedule(medicineName, idNumber, events);

        // Iterate through the list of times
        for(Map<String, Double> time : times){
            // Add an event to the list of events in the schedule.
            myMedicineSchedule.addEvent(medicineName, description, time);
        }


    }

    public String makeDescription() {
        String description;
        if (amount == -1){
            description = methodOfAdministration + " " +
                    medicineName + ". " +
                    extraInstructions;
        } else {
            description = methodOfAdministration + " " + amount + " " +
                    medicineName + ". " +
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

//    /**
//     * Gets the Amount in this Medicine Class
//     * @return  The Amount in this Medicine Class
//     */
//    public int getAmount(){
//        return this.amount;
//    }
//
//    /**
//     * Gets the methodOfAdministration in this Medicine Class
//     * @return  The methodOfAdministration in this Medicine Class
//     */
//    public String getMethodOfAdministration() {
//        return methodOfAdministration;
//    }
//
//    /**
//     * Gets the extraInstructions in this Medicine Class
//     * @return  The extraInstructions in this Medicine Class
//     */
//    public String getExtraInstructions() {
//        return extraInstructions;
//    }
//
//    /**
//     * Gets the idNumber in this Medicine Class
//     * @return  The idNumber in this Medicine Class
//     */
//    public int getIdNumber() {
//        return idNumber;
//    }

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

    public String[] getMedicineInfo(){
        String[] info = new String[5 + myMedicineSchedule.getNumberOfEvents()];
        String[] listOfMedicineTimes;
        info[0] = "Name of Medicine: " + medicineName;
        info[1] = "Amount of Medicine: ";

        // We will return the amount if there is a value associated with it.
        if (amount >= 0){
            info[1] = info[1] + amount;
        }

        info[2] = "Method of Administration: " + methodOfAdministration;
        info[3] = "Extra Instructions: " + extraInstructions;
        info[4] = "Times: ";
        listOfMedicineTimes = myMedicineSchedule.getEventTimes();

        // Format the times:
        for (int i = 5; i < info.length; i++){
            info[i] = "   - " + listOfMedicineTimes[i - 5];
        }

        return info;
    }
}
