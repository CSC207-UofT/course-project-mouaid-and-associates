package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import java.util.Arrays;
import java.util.Scanner;

public class ChoosePrescriptionToEditWindow extends Window implements DisplayEntityInformation {
    private String[] validInputs;
    public ChoosePrescriptionToEditWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info   The information to be printed. In this case, a list of prescription names,
     *               and a title.
     */
    @Override
    public void displayInfo(String[] info) {
        for (String line: info){
            System.out.println(line);
        }

        // Also add the list of medicine names as valid inputs to verify user input.
        setValidInputs(Arrays.copyOfRange(info, 0, info.length));
    }

    private void setValidInputs(String[] inputs){
        validInputs = inputs;
    }

    @Override
    public String[] getUserInput() {
        String[] userInput = new String[1];
        boolean validInput = false;
        System.out.println("Please enter the name of the prescription you would like to edit:");

        while(!validInput){
            userInput[0] = scanner.nextLine();
            validInput = verifyInput(userInput[0]);
            if (!validInput){
                System.out.println("Please enter a valid prescription name.");
            }
        }

        return userInput;
    }

    private boolean verifyInput(String input){
        for (String validInput: validInputs){
            if (validInput.equals(" - " + input)){
                return true;
            }
        }
        return false;
    }
    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {

    }


    @Override
    public void createView() {

    }
}
