package frameworks_and_drivers;

import interface_adapters.PrescriptionWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EditPrescriptionWindow extends PrescriptionWindow {

    public EditPrescriptionWindow(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String[] getUserInput() {
        return new String[0];
    }

    /**
     * @return the index of the medicine at even indexes and the new medicine at odd indexes, 0'th index
     * is the name of the prescription, 1st index is the new name of that prescription
     */
    public List<String[]> getUserPrescriptionInput() {

        List<String[]> medicines = new ArrayList<>();
/*        String choice = "";
        while(!choice.equals("add") && !choice.equals("remove")) {
            System.out.println("Add or edit this this prescription");
            choice = scanner.nextLine();
        }*/
        System.out.println("Name of the prescription you want to edit");
        String prescription = scanner.nextLine();
        String[] prescriptionList = {prescription};
        medicines.add(prescriptionList);
        medicines.add(prescriptionList);
        //String[] presToEdit = choose

        System.out.println("Select one of the options below: ");
        System.out.println("(1) Change the prescription name");
        System.out.println("(2) Add a Medicine to this prescription");
        System.out.println("(3) Remove a Medicine from this prescription");
        System.out.println("(4) Edit a Medicine from this prescription");
        System.out.println("(5) Quit");

        String choice = scanner.nextLine();
        while (!choice.equals("5")){
            choice = scanner.nextLine();
            switch (choice) {
                case "4":
                    editMedicineHelper(medicines);
                    break;
                case "3":
                    removeMedicineHelper(medicines);
                    break;
                case "2":
                    addMedicineHelper(medicines);
                    break;
                case "1":
                    System.out.println("Enter new prescription name");
                    String[] newName = {scanner.nextLine()};
                    medicines.add(1, newName);
                    break;
            }
        }
        return medicines;



    }

    private void editMedicineHelper(List<String[]> medicines){
        String medicineChoice = "";
        while (medicineChoice.equals("")) {
            System.out.println("Specify which medicine to edit (by name)");
            medicineChoice = scanner.nextLine();
        }
        EditMedicineWindow editMedicineWindow = new EditMedicineWindow(scanner);
        String[] medicine = {medicineChoice};
        medicines.add(medicine);
        medicines.add(editMedicineWindow.getUserInput());
    }
    private void addMedicineHelper(List<String[]> medicines) {
        AddMedicineWindow addMedicineWindow = new AddMedicineWindow(scanner);
        String[] newMed = addMedicineWindow.getUserInput();
        String[] newMedName= {newMed[0]};
        medicines.add(newMedName);
        medicines.add(newMed);
    }

    private void removeMedicineHelper(List<String[]> medicines) {
        RemoveMedicineWindow removeMedicineWindow = new RemoveMedicineWindow(scanner);
        String[] newMed = removeMedicineWindow.getUserInput();
        String[] newMedName= {newMed[0]};
        String[] emptyMed = {"-1"};
        medicines.add(newMedName);
        medicines.add(emptyMed);
    }

}