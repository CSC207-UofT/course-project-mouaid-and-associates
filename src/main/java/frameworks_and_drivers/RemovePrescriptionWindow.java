package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import java.util.Scanner;

public class RemovePrescriptionWindow extends Window {
    public RemovePrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
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

    /**
     * Notify the observer of a change
     *
     * @param frame
     * @param source
     */
    @Override
    public void update(ObservableFrame frame, Object source) {

    }


    @Override
    public void createView() {

    }
}
