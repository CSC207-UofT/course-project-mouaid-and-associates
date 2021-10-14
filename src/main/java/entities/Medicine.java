package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Medicine {

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
        String description = new String(methodOfAdministration + " " + medicineName);

        List<Event> events = new ArrayList<>();

        // Iterate through the list of times
        for(Map<String, Double> time : times){

            // Make an event for each time stamp and add that to the list of events.
            Event event = new Event(medicineName, description, time);
            events.add(event);
        }
        // Make a schedule
        this.myMedicineSchedule = new MedicineSchedule(medicineName, idNumber, events);
    }

    public MedicineSchedule getMyMedicineSchedule() {
        return myMedicineSchedule;
    }

    public String getMedicineName(){
        return this.medicineName;
    }

    public int getAmount(){
        return this.amount;
    }

    public String getMethodOfAdministration() {
        return methodOfAdministration;
    }

    public String getExtraInstructions() {
        return extraInstructions;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setMethodOfAdministration(String methodOfAdministration) {
        this.methodOfAdministration = methodOfAdministration;
    }

    public void setExtraInstructions(String extraInstructions) {
        this.extraInstructions = extraInstructions;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }
}
