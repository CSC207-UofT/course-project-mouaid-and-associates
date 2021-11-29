package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RemoveMedicineWindow extends Window implements DisplayEntityInformation {

    public RemoveMedicineWindow(Scanner scanner) {
        super(scanner);
    }

    @Override
    public String[] getUserInput() {
        List<String> inputs = new ArrayList<>();
        String input = " ";

        // Get a list of all the medicines the user would like to remove
        while (!input.equals("")){
            input = getUserChoice();
            inputs.add(input);
        }

        // Return the list of inputs.
        return inputs.toArray(new String[0]);

    }

    public String getUserChoice(){
        System.out.println("Please enter the name of the medicine you would like to remove below:");
        System.out.println("* You can just press enter to exit *");

        return scanner.nextLine();
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info      The list of medicine to be printed.
     */
    @Override
    public void displayInfo(String[] info) {
        // For every sentence in info, print it out.
        for (String sentence: info){
            System.out.println(sentence);
        }
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
