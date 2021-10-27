package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.util.Scanner;

public class EditMedicineWindow extends Window {
    public EditMedicineWindow(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String[] getUserInput() {
        //TODO: Ask the user using multiple choice on which attribute do they want to change
        //TODO: Return an array with the same order of items as the parameters of addMedicine.
        //TODO: Instantiate different variables for the name, amount, method of admin etc.
        //TODO: For times, follow the format of addMedicine in that we ask the user of the times all over again.
        //      (may need to rethink this part)
        return new String[0];
    }
}
