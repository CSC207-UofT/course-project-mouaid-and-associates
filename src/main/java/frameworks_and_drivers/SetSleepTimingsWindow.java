package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetSleepTimingsWindow extends Window {
    /**
     * The window that displays the Set Sleeping Time page.
     */

    private List<JTextField> changes;
    private JPanel panel;
    private volatile List<String> userInput;


    public SetSleepTimingsWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        createView();
        userInput = new ArrayList<>();

    }

    /**
     * Gets input from the user in order to Set the Sleeping Time.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        String[] userResponse = new String[]{"", ""};

        while (!(super.userResponded && userInput.size() >= 2)){

            // Only when we have a valid number of inputs do
            // we get all the user input.
            if (userInput.size() >= 2) {
                userResponse = userInput.toArray(new String[0]);
            }
        }

        // Clear the input list so that we can use it again next time.
        userInput.clear();

        // Empty all the text boxes
        resetTextFields();

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

            for (int i = 0; i < changes.size(); i++) {
                userInput.add(changes.get(i).getText());
            }

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

        // Instantiate the two text boxes
        JLabel sleepLabel = new JLabel("Please enter your Sleep time: ");
        sleepLabel.setSize(286, 40);
        sleepLabel.setLocation(100, 50);
        JTextField sleepTime = new JTextField();
        sleepTime.setSize(286, 50);
        sleepTime.setLocation(100, 100);

        JLabel wakeUpLabel = new JLabel("Please enter your Wake up time: ");
        wakeUpLabel.setSize(286, 40);
        wakeUpLabel.setLocation(100, 250);
        JTextField wakeUpTime = new JTextField();
        wakeUpTime.setSize(286, 50);
        wakeUpTime.setLocation(100, 300);

        changes = new ArrayList<>();
        changes.add(sleepTime);
        changes.add(wakeUpTime);

        // Create the button.
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setFont(buttonFont);
        saveButton.setSize(400, 70);
        saveButton.setLocation(43, 400);


        // Add the items to the panel
        panel.add(sleepLabel);
        panel.add(sleepTime);
        panel.add(wakeUpLabel);
        panel.add(wakeUpTime);
        panel.add(saveButton);

        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // Set the view for this window.
        super.view = panel;

    }

    /**
     * Resets all the text fields to contain an empty string.
     */
    private void resetTextFields(){
        for (JTextField textField: changes){
            textField.setText("");
        }
    }
}
