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
    private SelectTimesWindow selectTimes;
    private JTextField name;
    private JTextField methodOfAdministration;
    private JTextField unitOfMeasurement;
    private JTextField amount;
    private JTextField extra;
    private JButton inputTimes;
    private JTextField wOrD;
    private JTextField startDay;
    private JTextField startMonth;
    private JTextField howManyTimes;
    private String[] times;


    public AddMedicineWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        this.selectTimes = selectTimes;
        createView();
        times = new String[0];

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
        String[] returnList = new String[0];
        boolean askedTimes = false;
        while (!(super.userResponded && returnList.length >= 9)){

            if (super.userResponded && !askedTimes){
                selectTimes.setNumTimes(Integer.parseInt(howManyTimes.getText()));
                selectTimes.updateFrame();
                times = selectTimes.getUserInput();
                askedTimes = true;
            }

            // Only when we have a valid number of inputs do
            // we get all the user input.
            if (userInput.size() >= 8 && askedTimes) {
                returnList = new String[times.length + userInput.size()];
                for (int i = 0; i < userInput.size(); i++){
                    returnList[i] = userInput.get(i);
                }
                for (int i = userInput.size(); i < userInput.size() + times.length; i++){
                    returnList[i] = times[i - userInput.size()];
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
            userInput.add(wOrD.getText());
            userInput.add(startDay.getText());
            userInput.add(startMonth.getText());

            super.userResponded = true;
        }

    }

    @Override
    public void createView() {
        // Create a new panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        name = new JTextField("");
        methodOfAdministration = new JTextField("");
        unitOfMeasurement= new JTextField("");
        amount= new JTextField("");
        extra= new JTextField("");
        wOrD = new JTextField("");
        startDay = new JTextField("");
        startMonth = new JTextField("");
        howManyTimes = new JTextField("");

        JLabel nameLabel = new JLabel("Medicine Name: ");
        JLabel methodOfAdministrationLabel = new JLabel("How are you taking the medicine? ");
        JLabel unitOfMeasurementLabel = new JLabel("Unit of measurement (e.g. pill, mL, tsp, mg, etc.: ");
        JLabel amountLabel = new JLabel("Enter the amount as an integer: ");
        JLabel extraLabel = new JLabel("Additional information");
        JLabel wOrDLabel = new JLabel("Do you need to take it weekly or daily?");
        JLabel startDayLabel = new JLabel("What day of the month would you like to start?");
        JLabel startMonthLabel = new JLabel("What month would you like to start? Input a number, like 1 for January");
        JLabel howManyTimesLabel = new JLabel("How many times do you need to take this medicine?");

        nameLabel.setSize(200, 40);
        name.setSize(200, 50);
        nameLabel.setLocation(100, 50);
        name.setLocation(100, 100);

        methodOfAdministrationLabel.setSize(200, 40);
        methodOfAdministration.setSize(200, 50);
        methodOfAdministrationLabel.setLocation(100, 150);
        methodOfAdministration.setLocation(100, 200);

        unitOfMeasurementLabel.setSize(200, 40);
        unitOfMeasurement.setSize(200, 50);
        unitOfMeasurementLabel.setLocation(100, 250);
        unitOfMeasurement.setLocation(100, 300);

        amountLabel.setSize(200, 40);
        amount.setSize(200, 50);
        amountLabel.setLocation(100, 350);
        amount.setLocation(100, 400);

        extraLabel.setSize(200, 40);
        extra.setSize(200, 50);
        extraLabel.setLocation(100, 450);
        extra.setLocation(100, 500);

        wOrDLabel.setSize(200, 40);
        wOrD.setSize(200, 50);
        wOrDLabel.setLocation(100, 550);
        wOrD.setLocation(100, 600);

        startDayLabel.setSize(200, 40);
        startDay.setSize(200, 50);
        startDayLabel.setLocation(100, 650);
        startDay.setLocation(100, 700);

        startMonthLabel.setSize(200, 40);
        startMonth.setSize(200, 50);
        startMonthLabel.setLocation(100, 750);
        startMonth.setLocation(100, 800);

        howManyTimesLabel.setSize(200, 40);
        howManyTimes.setSize(200, 50);
        howManyTimesLabel.setLocation(100, 850);
        howManyTimes.setLocation(100, 900);



        inputTimes = new JButton("Input Times");
        inputTimes.setLocation(100, 970);
        inputTimes.setSize(100, 70);



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
        panel.add(wOrD);
        panel.add(wOrDLabel);
        panel.add(startDayLabel);
        panel.add(startDay);
        panel.add(startMonthLabel);
        panel.add(startMonth);
        panel.add(inputTimes);
        panel.add(howManyTimesLabel);
        panel.add(howManyTimes);

        super.buttonResponses.put(inputTimes, "0");
        super.addActionListenerToAllButtons();
        panel.setPreferredSize(new Dimension(486, 1060));
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

}

