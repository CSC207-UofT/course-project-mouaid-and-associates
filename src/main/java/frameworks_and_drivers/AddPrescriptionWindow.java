package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddPrescriptionWindow extends Window {
    /**
     * This is the window where the user can add a prescription, which groups pre-existing medicine together.
     *
     * Instance Attributes:
     * - name: The text field where the user can put information
     * - medicineNames: The text field where the user can put the medicine names
     * - userInput: An ArrayList that stores all of the user's input for the current try
     * - nameLabel: the label for the name
     * - medicineNamesLabel: the label for user input
     *
     */
    JTextField name;
    JTextField medicineNames;
    private ArrayList<String> userInput;
    private JLabel nameLabel;
    private JLabel medicineNamesLabel;

    public AddPrescriptionWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        userInput = new ArrayList<>();
        createView();
    }

    /**
     * Gets the user input for the current try.
     * @return An array that represents the name of the prescription in the first index and the medicine names that
     * should be added to the prescription separated by commas in the second
     */
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[2];
        if (super.userResponded && userInput.size() == 2){
                returnList[0] = userInput.get(0);
                returnList[1] = userInput.get(1);
            }
        return returnList;
    }


    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {
        userInput = new ArrayList<>();
        if (super.buttonResponses.containsKey(source)){
            userInput.add(name.getText());
            userInput.add(medicineNames.getText());
            super.userResponded = true;
        }
    }

    /**
     * Creates the view for this window, which contains a place to enter the name of the prescription and
     * input the medicine names
     */
    @Override
    public void createView() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);
        name = new JTextField("");
        medicineNames = new JTextField("");

        //Create the labels and set the locations
        nameLabel = new JLabel("Name of Prescription:");
        medicineNamesLabel = new JLabel("Name of existing medicines. Separate by comma:");
        nameLabel.setSize(200, 40);
        name.setSize(200, 50);
        nameLabel.setLocation(100, 50);
        name.setLocation(100, 100);

        medicineNamesLabel.setSize(400, 40);
        medicineNames.setSize(200, 50);
        medicineNamesLabel.setLocation(50, 150);
        medicineNames.setLocation(100, 200);

        //Create a button so that the program knows when they are done and can check and return to the home page
        JButton create = new JButton("Create Medicines");
        create.setLocation(100, 250);
        create.setSize(100, 70);

        //Add the labels and text fields to the panels
        panel.add(name);
        panel.add(nameLabel);
        panel.add(medicineNames);
        panel.add(medicineNamesLabel);
        panel.add(create);
        super.buttonResponses.put(create, "0");
        super.addActionListenerToAllButtons();


        super.view = panel;

    }
}
