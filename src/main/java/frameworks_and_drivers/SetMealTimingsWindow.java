package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SetMealTimingsWindow extends Window {
    /**
     * The window that displays the Set Meal Time page.
     */

    private JTextField mealNumber;
    private SelectTimesWindow selectTimes;
    private String[] times;
    private JPanel panel;
    private volatile List<String> userInput;


    public SetMealTimingsWindow(Scanner scanner, ObservableFrame frame, SelectTimesWindow selectTimes){
        super(scanner, frame);
        this.selectTimes = selectTimes;
        mealNumber = new JTextField();
        createView();
        userInput = new ArrayList<>();

    }

    /**
     * Gets input from the user in order to Set the Sleeping Time.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        String[] userResponse = new String[0];
        boolean askedTimes = false;

        while (!(super.userResponded && userResponse.length >= 1)){

            if (super.userResponded && !askedTimes){
//                selectTimes.setNumTimes(Integer.parseInt(changes.get(0).getText()));
                selectTimes.setNumTimes(Integer.parseInt(mealNumber.getText()));
                selectTimes.updateFrame();
                times = selectTimes.getUserInput();
                askedTimes = true;
            }
            // Only when we have a valid number of inputs do
            // we get all the user input.
            if (userInput.size() == 1 && askedTimes) {
                userResponse = times;
                System.out.println(Arrays.toString(userResponse));
            }

        }

        // Clear the input list so that we can use it again next time.
        userInput.clear();

        // Empty the text box
        mealNumber.setText("");

        // Return user input.
        return userResponse;
    }

    /**
     * Sets userInput if the source is the save button in this view.
     * This method is synchronized so that when multiple threads
     * access this variable, we can ensure that only one thread
     * can access this method at a time.
     * @param source   The source of the event.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            // Since changes is an ordered list of text boxes, thus
            // we just add the items to the userInput.

            userInput.add(mealNumber.getText());
//            for (int i = 0; i < changes.size(); i++) {
//                userInput.add(changes.get(i).getText());
//            }

            // Best to do this after you make all your changes.
            super.userResponded = true;
        }
    }

    @Override
    public void createView() {
        // Create a new JPanel
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

        // Instantiate the text box
        JLabel mealLabel = new JLabel("Please enter the number of Meals: ");
        mealLabel.setSize(286, 40);
        mealLabel.setLocation(100, 50);
        mealNumber.setSize(286, 50);
        mealNumber.setLocation(100, 100);


        // Create the button.
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        JButton saveButton = new JButton("NEXT");
        saveButton.setFont(buttonFont);
        saveButton.setSize(400, 70);
        saveButton.setLocation(43, 400);

//        changes = new ArrayList<>();
//        changes.add(numberOfMeals);

        // Add the items to the panel
        panel.add(mealLabel);
        panel.add(mealNumber);
        panel.add(saveButton);

        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // Set the view for this window.
        super.view = panel;

    }

//    /**
//     * Resets all the text fields to contain an empty string.
//     */
//    private void resetTextFields(){
//        for (JTextField textField: changes){
//            textField.setText("");
//        }
//    }
}
