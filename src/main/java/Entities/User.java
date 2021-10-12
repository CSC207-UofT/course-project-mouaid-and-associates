package Entities;

import java.util.HashMap;
import Entities.Medicine;

public class User {

    private String name;
    private String userName;
    private HashMap<String, Medicine> medicineList;

    public User(String name, String userName){
        this.name = name;
        this.userName = userName;
        this.medicineList = new HashMap<String,  Medicine>();
    }

    public String getName(){
        return this.name;
    }

    public String getUserName(){
        return this.userName;
    }

    public HashMap<String, Medicine> getMedicineList(){
        return this.medicineList;
    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void addMedicine(Medicine newMedicine){
        if (!(this.medicineList.containsKey(newMedicine))){
            this.medicineList.put(newMedicine.getMedicineName(), newMedicine);
            newMedicine.setIdNumber(this.medicineList.size());
        }
    }

    public void removeMedicine(Medicine newMedicine){
        if (this.medicineList.containsKey(newMedicine)){
            this.medicineList.remove(newMedicine.getMedicineName(), newMedicine);
            newMedicine.setIdNumber(0);
        }
    }

}

