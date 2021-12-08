package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class AddMedicineWindow extends Window {
    /**
     * The window that displays the add a Medicine page. It allows the user to input details about a medicine
     * then it calls SelectTimesWindow to select the times for the medicine.
     *
     * Instance Attributes:
     * - userInput: A list storing the user's responses
     * - selectTimes: The selectTimes window that is called to get the times for the medicine
     * - inputTimes: The button that is pressed to move to the selectTimesWindow
     * - times: represents the times to take the medicine, which is taken from selectTimesWindow
     * - textList: stores all the textboxes that are on the panel
     * - labelList: stores all the labels that are on the panel
     * - labelText: represents what will be in the labels and the order they are in

     **/
    private ArrayList<String> userInput;
    private SelectTimesWindow selectTimes;
    private JButton inputTimes;
    private String[] times;
    private List<JTextField> textList;
    private List<JLabel> labelList;
    static String[] labelText = {"Medicine Name: ", "How are you taking the medicine? ",
            "Unit of measurement (e.g. pill, mL, tsp, mg, etc.): ", "Enter the amount as an integer: ",
            "Additional information", "Do you need to take it weekly or daily?",
            "What day of the month would you like to start?",
            "What month would you like to start? Input a number, like 1 for January",
            "How many times do you need to take this medicine?"
    };


    public AddMedicineWindow(Scanner scanner, ObservableFrame frame, SelectTimesWindow selectTimes) {
        super(scanner, frame);
        this.selectTimes = selectTimes;
        userInput = new ArrayList<>();
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
        //Keep asking until the user responds
        while (!(super.userResponded && returnList.length >= 9)){

            //Ensures that the number of times to ask for the time is an integer
            if (super.userResponded && !checker.isNumeric(textList.get(8).getText())) {
                super.userResponded = false;
                //Create a pop-up to display the error message
                JOptionPane.showMessageDialog(super.frame, "Please input an integer", "Warning for \"How many times to take medicine\"!",
                        JOptionPane.INFORMATION_MESSAGE);
                userInput.clear();
            }

            //Ensures that the amount of medicine is an integer
            if (super.userResponded && (!checker.isNumeric(textList.get(3).getText()) ||
                    Integer.parseInt(textList.get(3).getText()) <= 0)){
                super.userResponded = false;
                JOptionPane.showMessageDialog(super.frame, "Amount of medicine should be an integer", "Warning for \"Enter Amount as Integer!\"",
                        JOptionPane.INFORMATION_MESSAGE);
                userInput.clear();
            }

            //Ensures that the month is an integer and is a valid one
            if (super.userResponded && (!checker.isNumeric(textList.get(7).getText()) ||
                    !checker.isValidMonth(textList.get(7).getText()))){
                super.userResponded = false;
                JOptionPane.showMessageDialog(super.frame, "The month must be an integer from 1-12", "Warning for select month!",
                        JOptionPane.INFORMATION_MESSAGE);

                userInput.clear();
            }

            //Ensures that the day is actually a day of the month
            if (super.userResponded && (!checker.isNumeric(textList.get(6).getText()) ||
                    !checker.isValidDay(textList.get(6).getText(), textList.get(7).getText()))){
                super.userResponded = false;
                JOptionPane.showMessageDialog(super.frame, "The month doesn't have that many days", "Warning for select day of month!",
                        JOptionPane.INFORMATION_MESSAGE);

                userInput.clear();
            }

            //Ensures that the user selects daily or weekly
            if (super.userResponded && !checker.isWeekOrDaily(textList.get(5).getText())){
                super.userResponded = false;
                JOptionPane.showMessageDialog(super.frame, "It must be weekly or daily", "Warning for weekly or daily!",
                        JOptionPane.INFORMATION_MESSAGE);
                userInput.clear();
            }
            //If we have not asked the user for times, ask now
            if (super.userResponded && !askedTimes){
                selectTimes.setNumTimes(Integer.parseInt(textList.get(8).getText()));
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

        // Clear user input and reset the window
        resetTextFields();
        userInput.clear();

        // Return user input
        return returnList;
    }

    /**
     * Update userInput based on if the event originated from this window.
     *
     * @param source    The button that is the source of the event.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            for (int i = 0; i < 8; i ++){
                userInput.add(textList.get(i).getText());
            }

            super.userResponded = true;
        }


    }

    /**
     * Creates the panel where it asks users to input times
     */
    @Override
    public void createView() {
        // Create a new panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        textList = new ArrayList<>();
        labelList = new ArrayList<>();
        //Create the text boxes and labels and store them
        for (int i = 0; i < 9; i++){
            JTextField texts = new JTextField("");
            texts.setSize(200, 50);
            texts.setLocation(100, 100 * i + 100 );
            JLabel label = new JLabel(labelText[i]);
            label.setSize(400, 40);
            label.setLocation(43, 100 * i + 50);


            textList.add(texts);
            panel.add(texts);
            labelList.add(label);
            panel.add(label);
        }

        //Create the button
        inputTimes = new JButton("Input Times");
        inputTimes.setLocation(100, 970);
        inputTimes.setSize(100, 70);
        panel.add(inputTimes);

        // Add an action listener to the button and resize the window.
        super.buttonResponses.put(inputTimes, "0");
        super.addActionListenerToAllButtons();
        panel.setPreferredSize(new Dimension(486, 1080));
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * Resets all the text fields to contain an empty string.
     */
    private void resetTextFields(){
        for (JTextField textField: textList){
            textField.setText("");
        }
    }

}

