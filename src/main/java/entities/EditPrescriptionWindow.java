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
     * @return the index of the medicine at even indexes and the new medicine at odd indexes
     */
    public List<String[]> getUserPrescriptionInput() {

        List<String[]> medicines = new ArrayList<>();
/*        String choice = "";
        while(!choice.equals("add") && !choice.equals("remove")) {
            System.out.println("Add or edit this this prescription");
            choice = scanner.nextLine();
        }*/

        String done = "";
        while (!done.equals("0")) {
            String medicineChoice = "";
            while (!isNumeric(medicineChoice)) {
                System.out.println("Specify which medicine to edit (by number)");
                medicineChoice = scanner.nextLine();
            }
            EditMedicineWindow editMedicineWindow = new EditMedicineWindow(scanner);
            int medInt = Integer.parseInt(medicineChoice) - 1;
            String[] medicineNumber = {String.valueOf(medInt)};
            medicines.add(medicineNumber);
            medicines.add(editMedicineWindow.getUserInput());

            System.out.println("If you are done editing the Prescription press 0.");
            done = scanner.nextLine();
        }

        return medicines;


    }
    /**
     * Checks to see whether str can be converted into a double
     * @param str what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    private  boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }

    }
}

