package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.util.Scanner;

public class ChooseMedicineToEditWindow extends Window implements DisplayEntityInformation {
    public ChooseMedicineToEditWindow(Scanner scanner) {
        super(scanner);
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info
     */
    @Override
    public void displayInfo(String[] info) {

    }

    @Override
    public String[] getUserInput() {
        return new String[0];
    }
}
