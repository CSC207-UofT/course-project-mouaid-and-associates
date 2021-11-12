package entities;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionMedicine {
    private List<Medicine> listOfMedicines;
    private String prescriptionName;

    public PrescriptionMedicine(List<Medicine> listOfMedicines, String prescriptionName){
        this.listOfMedicines = listOfMedicines;
        this.prescriptionName = prescriptionName;
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

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String[] getPresMedicines(){
        String[] meds = new String[listOfMedicines.size()];
        for(int i = 0; i < listOfMedicines.size(); i++){
            meds[i] = listOfMedicines.get(i).getMedicineName();
        }
        return meds;
    }

    public List<Medicine> getPresMedicinesMedicine(){
        return this.listOfMedicines;
    }
}