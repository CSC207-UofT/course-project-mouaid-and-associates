package entities;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionMedicine {
    private List<Medicine> listOfMedicines;
    private int prescriptionID;

    public PrescriptionMedicine(List<Medicine> listOfMedicines, int prescriptionID){
        this.listOfMedicines = listOfMedicines;
        this.prescriptionID = prescriptionID;
    }

    /**
     * Adds a medicine to the list of medicines for this prescription
     * @param medicine the medicine that will be added to the list
     */
    public void addMedicine(Medicine medicine){
        this.listOfMedicines.add(medicine);
    }

    public void removeMedicine(String name){
        listOfMedicines.removeIf(medicine -> medicine.getMedicineName().equals(name));
    }

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public String[] getPresMedicines(){
        String[] meds = new String[listOfMedicines.size()];
        for(int i = 0; i < listOfMedicines.size(); i++){
            meds[i] = listOfMedicines.get(i).getMedicineName();
        }
        return meds;
    }
}
