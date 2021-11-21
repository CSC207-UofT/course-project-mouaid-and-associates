package entities;

import java.util.List;

public class MedicineSchedule extends Schedule {
    /**
     * A schedule of all the events of a singular Medicine
     * Instance attributes:
     * medicineName: The name of the medicine corresponding to this MedicineSchedule.
     * id: A unique ID number to identify the medicine corresponding to this MedicineSchedule in the
     * User's medicine list.
     */
    private String medicineName;

    public MedicineSchedule(String name, List<Event> events){
        super(events);
        this.medicineName = name;
    }

    /**
     * Gets the medicineName in this MedicineSchedule
     * @return  The medicineName in this MedicineSchedule
     */
    public String getMedicineName() {
        return medicineName;
    }

    /**
     * Sets the medicineName in this MedicineSchedule
     * @param medicineName The medicineName to be set to this MedicineSchedule
     */
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

}
