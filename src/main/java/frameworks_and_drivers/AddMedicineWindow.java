package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.ScheduleInputWindow;
import interface_adapters.Window;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;

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
     * - LABELTEST: represents what will be in the labels and the order they are in
     **/
    private ArrayList<String> userInput;
    private SelectTimesWindow selectTimes;
    private JButton inputTimes;
    private String[] times;
    private List<JTextField> textList;
    private List<JLabel> labelList;
    static String[] LABELTEXT = {"Medicine Name: ", "How are you taking the medicine? ",
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
        while (!(super.userResponded && returnList.length >= 9)){
            if (super.userResponded && !isNumeric(textList.get(8).getText())) {
                super.userResponded = false;
                labelList.get(8).setForeground(Color.RED);
                userInput = new ArrayList<>();
            }

            if (super.userResponded && (!isNumeric(textList.get(3).getText()) ||
                    Integer.parseInt(textList.get(3).getText()) <= 0)){
                super.userResponded = false;
                labelList.get(3).setForeground(Color.RED);
                userInput = new ArrayList<>();
            }

            if (super.userResponded && !isNumeric(textList.get(6).getText())){
                super.userResponded = false;
                labelList.get(6).setForeground(Color.RED);
                userInput = new ArrayList<>();
            }
            if (super.userResponded && (!isNumeric(textList.get(7).getText()))){
                super.userResponded = false;
                labelList.get(7).setForeground(Color.RED);
                userInput = new ArrayList<>();
            }

            if (super.userResponded && !textList.get(5).getText().equals("daily") &&
                    !textList.get(5).getText().equals("weekly")){
                super.userResponded = false;
                labelList.get(5).setForeground(Color.RED);
                userInput = new ArrayList<>();
            }
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
        resetTextFields();
        return returnList;
    }

    /**
     * Notify the observer of a change
     *
     * @param source
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey(source)){
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
            JLabel label = new JLabel(LABELTEXT[i]);
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
    /**
     * Checks to see whether str can be converted into a double
     * @param text what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    private boolean isNumeric(String text) {
        try {
            Double.parseDouble(text);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}

