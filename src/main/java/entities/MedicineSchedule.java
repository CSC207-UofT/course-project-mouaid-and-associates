package entities;

import java.util.List;

public class MedicineSchedule extends Schedule {
    private String medicineName;
    private int id;

    public MedicineSchedule(String name, int id, List<Event> events){
        super(events);
        this.medicineName = name;
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
