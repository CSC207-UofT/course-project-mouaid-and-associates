package Entities;

public class Medicine {

    private String medicineName;
    private int amount;
    private String methodOfAdministration;
    private String extraInstructions;
    private int idNumber;
    private MedicineSchedule myMedicineSchedule;

    //TODO: make a medicineSchedule
    public Medicine(String medicineName, int amount, String methodOfAdministration, String extraInstructions) {
        this.medicineName = medicineName;
        this.amount = amount;
        this.methodOfAdministration = methodOfAdministration;
        this.extraInstructions = extraInstructions;
        this.idNumber = 0;
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
