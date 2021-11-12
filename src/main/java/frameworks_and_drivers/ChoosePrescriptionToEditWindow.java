package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.util.Arrays;
import java.util.Scanner;

public class ChoosePrescriptionToEditWindow extends Window implements DisplayEntityInformation {
    private String[] validInputs;
    public ChoosePrescriptionToEditWindow(Scanner scanner){
        super(scanner);
    }

    /**
     * Displays the list of prescriptions that the user can select to edit.
     * @param info      The list of prescriptions the user can edit.
     */
    @Override
    public void displayInfo(String[] info) {
        for (String line: info){
            System.out.println(line);
        }
        setValidInputs(Arrays.copyOfRange(info, 1, info.length));
    }
    private void setValidInputs(String[] inputs){
        validInputs = inputs;
    }
    /**
     * Gets user input as to which prescription they want to edit:
     * @return      The name of the prescription to be edited.
     */
    @Override
    public String[] getUserInput() {
        //TODO: Implement this method.
        String[] userInput = new String[1];
        boolean validInput = false;
        System.out.println("What prescription would you like to edit? ");

        while (!validInput) {
            userInput[0] = scanner.nextLine();
            validInput = verifyInput(userInput[0]);
            if (!validInput) {
                System.out.println("Please enter the name of one a prescription in the list above");
            }
            return userInput;
        }
        return userInput; //just to run
    }


        private boolean verifyInput (String input){
            for (String validInput : validInputs) {
                if(validInput.equals(" - " + input)){
                    return true;
                }
            }
            return false;
        }

}