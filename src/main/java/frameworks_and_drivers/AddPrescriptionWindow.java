package frameworks_and_drivers;

import interface_adapters.PrescriptionWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddPrescriptionWindow extends PrescriptionWindow {


    public AddPrescriptionWindow(Scanner scanner){
        super(scanner);
    }

    @Override
    public String[] getUserInput() {
        return new String[0];
    }

    public List<String[]> getUserPrescriptionInput() {
        System.out.println("What would you like to name this prescription?");
        String presName = scanner.nextLine();
        // Add the prescription name into a string list, so it can be added to the return statement
        String[] presNameLst = new String[1];
        presNameLst[0] = presName;
        System.out.println("Number of medicines that come under this prescription");
        String noMedicines = scanner.nextLine();
        AddMedicineWindow medicineWindow = new AddMedicineWindow(scanner);
        List<String[]> medicines = new ArrayList<>();
        medicines.add(presNameLst);
        int accumulator = 0;
        while(accumulator != Integer.parseInt(noMedicines)){
            System.out.println("Enter the information for Medicine " + (accumulator + 1));
            medicines.add(medicineWindow.getUserInput());
            accumulator += 1;
        }

        return medicines;

    }
}

