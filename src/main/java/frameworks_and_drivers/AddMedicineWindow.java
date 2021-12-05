package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddMedicineWindow extends Window {
    /*
     * The window that displays the Add a Medicine page.
     */
    private ArrayList<String> userInput = new ArrayList<>();
    private JTextField name = new JTextField();
    private JTextField methodOfAdministration = new JTextField();
    private JTextField unitOfMeasurement = new JTextField();
    private JTextField amount = new JTextField();
    private JTextField extra = new JTextField();
    private JTextField times = new JTextField();


    public AddMedicineWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();


    }

    /**
     * Gets input from the user in order to add a medicine that the user uses in the app.
     *
     * The elements are the following order: medicine name, method of administration, the unit of
     * measurement of the medicine (i.e. it's type), the amount of medicine using the unit of measurement,
     * any extra instructions, the option of taking the medicine weekly or daily, and the starting date of
     * the medicine. All elements afterwords are the different times to take the medicine.
     * @return      A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[6];
        while (!(super.userResponded && userInput.size() >= 6)){

            // Only when we have a valid number of inputs do
            // we get all the user input.
            if (userInput.size() >= 6) {
                for (int i = 0; i < 6; i++){
                    returnList[i] = userInput.get(i);
                }
            }
        }
        return returnList;
    }

    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(ObservableFrame frame, Object source) {
        if (super.buttonResponses.containsKey(source)){
            super.userResponded = true;
            userInput.add(name.getText());
            userInput.add(methodOfAdministration.getText());
            userInput.add(unitOfMeasurement.getText());
            userInput.add(amount.getText());
            userInput.add(extra.getText());
            userInput.add(times.getText());
        }

    }

    @Override
    public void createView() {
        // Create a new panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel nameLabel = new JLabel("Medicine Name: ");
        JLabel methodOfAdministrationLabel = new JLabel("How are you taking the medicine? ");
        JLabel unitOfMeasurementLabel = new JLabel("Unit of measurement (e.g. pill, mL, tsp, mg, etc.: ");
        JLabel amountLabel = new JLabel("Enter the amount as an integer: ");
        JLabel extraLabel = new JLabel("Additional information");
        JLabel timesLabel = new JLabel("Enter how many times you want to take the medecine in a day");

        nameLabel.setSize(200, 40);
        name.setSize(200, 50);
        nameLabel.setLocation(100, 100);
        name.setLocation(100, 150);

        methodOfAdministrationLabel.setSize(200, 40);
        methodOfAdministration.setSize(200, 50);
        methodOfAdministrationLabel.setLocation(100, 250);
        methodOfAdministration.setLocation(100, 300);

        unitOfMeasurementLabel.setSize(200, 40);
        unitOfMeasurement.setSize(200, 50);
        unitOfMeasurementLabel.setLocation(100, 350);
        unitOfMeasurement.setLocation(100, 400);

        amountLabel.setSize(200, 40);
        amount.setSize(200, 50);
        amountLabel.setLocation(100, 450);
        amount.setLocation(100, 500);

        extraLabel.setSize(200, 40);
        extra.setSize(200, 50);
        extraLabel.setLocation(100, 550);
        extra.setLocation(100, 600);

        times.setSize(200, 50);
        timesLabel.setSize(200, 40);
        times.setLocation(100, 750);
        timesLabel.setLocation(100, 800);

        JButton inputTimes = new JButton("Input Times");
        inputTimes.setLocation(100, 850);
        inputTimes.setSize(100, 900);


        panel.add(nameLabel);
        panel.add(name);
        panel.add(methodOfAdministrationLabel);
        panel.add(methodOfAdministration);
        panel.add(unitOfMeasurementLabel);
        panel.add(unitOfMeasurement);
        panel.add(amountLabel);
        panel.add(amount);
        panel.add(extra);
        panel.add(extraLabel);

        super.addActionListenerToAllButtons();
        super.view = panel;
        super.buttonResponses.put(inputTimes, "0");
    }
}

