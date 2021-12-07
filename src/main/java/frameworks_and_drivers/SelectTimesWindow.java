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
     * - panel: It represents where everything will be displayed and contains all the components of the window.
     * - userInput: The text the user entered into the text boxes
     * - numTimes: Represents the number of text boxes we need
     * - textFields: A list containing the text boxes where the user will enter input
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

    /**
     * Updates the text field
     * @param source The source that causes the update
     */
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

    /**
     * Get's user input on the times to do a certain activity in a day.
     * @return      An array of strings denoting different times during the day.
     */
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[numTimes];
        while (!(super.userResponded && userInput.size() == numTimes)){
            // Only when we have a valid number of inputs do
            // we get all the user input.
            //Checks to see if the input is valid
            System.out.println("");
            if (super.userResponded && userInput.size() == numTimes){
                //Loop through the times and make sure they are valid
                for (int i = 0; i < numTimes; i++){
                    if(!checker.isValidTime(userInput.get(i))){
                        super.userResponded = false;
                        JOptionPane.showMessageDialog(super.frame, "Make sure the time is typed as \"XX:XX\"",
                                "Warning: Invalid Time entered for" + i +"'st time!", JOptionPane.INFORMATION_MESSAGE);
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
        panel.setLayout(null);;
        for (int i = 1; i < numTimes + 1; i++ ){
            JLabel timesLabel = new JLabel("For the " + i + "'st time what time do you need to take it? " +
                    "Enter in form XX:XX");
            JTextField textField = new JTextField("");
            timesLabel.setSize(400, 60);
            textField.setSize(200, 60);
            textField.setLocation(100, i * 100);
            timesLabel.setLocation(25, (i - 1) *  100 + 50);
            textFields.add(textField);
            panel.add(timesLabel);
            panel.add(textField);
        }

        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setSize(400, 70);
        saveButton.setLocation(43, numTimes * 150 + 150);
        panel.add(saveButton);
        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        panel.setPreferredSize(new Dimension(486, numTimes * 150 + 220));
        super.view = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    }

}