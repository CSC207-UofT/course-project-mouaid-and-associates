package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SelectTimesWindow extends Window {
    /**
     * This is a window that is called inside AddMedicineWindow, EditMedicineWindow, and SetMealTimingsWindow
     * in order to set the times and dates that those events should take place.
     *
     * Instance Attributes:
     * -panel: It represents where everything will be displayed and contains all the components of the window.
     * -userInput: The text the user entered into the text boxes
     * -medTimesLabel: All the times of the medicine
     * -numTimes: Represents the number of text boxes we need
     */
    private JPanel panel;
    private List<String> userInput;
    private int numTimes;
    private List<JTextField> textFields;

    public SelectTimesWindow(Scanner scanner, ObservableFrame frame){
        super(scanner,frame);
        // Rather than creating a view at the beginning, we will create a view
        // after we have set numTimes.
        userInput = new ArrayList<>();
        this.numTimes = 0;
        textFields = new ArrayList<>();
    }

    /**
     * Sets the number of times we need to ask for user input and
     * creates a view accordingly.
     * @param times     The number of new text boxes to be created.
     */
    public void setNumTimes(int times){
        numTimes = times;
        createView();
    }


    @Override
    public void update(Object source) {
        userInput = new ArrayList<>();
        if (super.buttonResponses.containsKey((JButton) source)){
            for (int i = 0; i < numTimes; i++ ){
                userInput.add(textFields.get(i).getText());
            }
            super.userResponded = true;
        }
    }

    @Override
    public String[] getUserInput() {
        String[] returnList = new String[numTimes];
        while (!(super.userResponded && userInput.size() == numTimes)){
            // Only when we have a valid number of inputs do
            // we get all the user input.

            //Checks to see if the input is valid
            if (super.userResponded && userInput.size() == numTimes){
                for (int i = 0; i < numTimes; i++){
                    if(!isValidTime(userInput.get(i))){
                        super.userResponded = false;
                        textFields.get(i).setForeground(Color.RED);
                        userInput = new ArrayList<>();
                        break;
                    }
                }
            }
            if (userInput.size() == numTimes && super.userResponded) {
                for (int i = 0; i < numTimes; i++){
                    returnList[i] = userInput.get(i);
                }
            }
        }
        userInput.clear();

        return returnList;
    }

    /**
     * Creates a view for this window.
     *
     * Preconditions:
     * - numTimes > 0
     */
    @Override
    public void createView() {
        // Initialize a JPanel and gives it a size.
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

        for (int i = 1; i < numTimes + 1; i++ ){
            JLabel timesLabel = new JLabel("For the " + i + "'st time what time do you need to take it? " +
                    "Enter in form XX:XX");
            JTextField textField = new JTextField("");
            timesLabel.setSize(200, 60);
            textField.setSize(200, 60);
            textField.setLocation(100, i * 100);
            timesLabel.setLocation(100, (i - 1) *  100 + 50);
            textFields.add(textField);
            panel.add(timesLabel);
            panel.add(textField);
        }

        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setSize(400, 70);
        saveButton.setLocation(100, numTimes * 150 + 150);
        panel.add(saveButton);
        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }


    /**
     * Checks to see whether str can be converted into a double
     * @param str what it will check to see if it can be converted to a string
     * @return Whether the str can be converted to a double
     */
    protected boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * Checks to see if the user inputted time correctly
     * @param time a string given that should represent the time
     * @return Whether time is of the right format
     */
    public boolean isValidTime(String time){
        if (time.length() != 5 || !isNumeric(String.valueOf(time.charAt(0))) ||
                !isNumeric(String.valueOf(time.charAt(1))) || !isNumeric(String.valueOf(time.charAt(3))) ||
                !isNumeric(String.valueOf(time.charAt(4))) || time.charAt(2) != ':'){
            return false;
        }
        else if (Character.getNumericValue(time.charAt(0)) * 10 + Character.getNumericValue(time.charAt(1)) >= 24 ||
                Character.getNumericValue(time.charAt(3)) * 10 + Character.getNumericValue(time.charAt(4)) > 59){
            return false;
        }
        else{
            return true;
        }


    }

}