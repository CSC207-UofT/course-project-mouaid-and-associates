package frameworks_and_drivers;

import interface_adapters.Window;

import java.util.Scanner;

public class RemovePrescriptionWindow extends Window {
    public RemovePrescriptionWindow(Scanner scanner){
        super(scanner);
    }



    @Override
    public String[] getUserInput() {
        System.out.println("What prescription would you like to remove? " +
                "Type prescription name as seen from the view panel");
        String prescription = scanner.nextLine();

        String[] returnedPres = new String[1];
        returnedPres[0] = prescription;
        return returnedPres;

    }
}
