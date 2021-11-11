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
     * @return the index of the medicine at odd indexes and the new medicine at even indexes, 0'th index
     * is the name of the prescription
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

        String done = "";
        while (!done.equals("0")) {
            String medicineChoice = "";
            while (!isNumeric(medicineChoice)) {
                System.out.println("Specify which medicine to edit (by name)");
                medicineChoice = scanner.nextLine();
            }
            EditMedicineWindow editMedicineWindow = new EditMedicineWindow(scanner);
            String[] medicine = {medicineChoice};
            medicines.add(medicine);
            medicines.add(editMedicineWindow.getUserInput());

            System.out.println("If you are done editing the Prescription press 0.");
            done = scanner.nextLine();
        }

        return medicines;



    }


}