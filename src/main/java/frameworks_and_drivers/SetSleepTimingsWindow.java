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
    private List<JLabel> changeLabels;
    private JPanel panel;
    private volatile List<String> userInput;
    private Font labelFont;
    private List<JLabel> infoOfTimes;

    public SetSleepTimingsWindow(Scanner scanner, ObservableFrame frame){
        super(scanner, frame);
        createView();
        userInput = new ArrayList<>();
        labelFont = new Font("SansSerif", Font.BOLD, 16);
        infoOfTimes = new ArrayList<>();
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

//        String[] returnList = new String[2];
//        System.out.println("Please enter your sleep time?");
//        String sleepTime = scanner.nextLine();
//        System.out.println("Please enter your wake up time");
//        String wakeUpTime = scanner.nextLine();
//        returnList[0] = sleepTime;
//        returnList[1] = wakeUpTime;
//        return returnList;
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
        // Create a new JPanel
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

        // Create a list of messages to print onto the labels:
        String[] labels = new String[]{"Please enter your sleep time?", "Please enter your wake up time?"};

        // Create all the text boxes but don't set their locations yet.
        changes = new ArrayList<>();
        for (int i = 0; i < 2; i++){
            JTextField textField = new JTextField("");
            textField.setSize(286, 60);
            changes.add(textField);
            panel.add(textField);
        }

        // Create all the corresponding labels but don't set their locations yet.
        changeLabels = new ArrayList<>();
        for (String label: labels){
            JLabel changeLabel = new JLabel(label);
            changeLabel.setSize(466, 60);
            changeLabel.setFont(labelFont);

            changeLabels.add(changeLabel);
            panel.add(changeLabel);
        }

        // Create the button.
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        JButton saveButton = new JButton("SAVE AND RETURN TO ACCOUNT PAGE");
        saveButton.setFont(buttonFont);
        saveButton.setSize(400, 70);

        panel.add(saveButton);

        // Add the button to the button map.
        super.buttonResponses.put(saveButton, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();


    }

    /**
     * Sets the locations for all the attributes of this class, specifically
     * changes, changeLabels, and saveButton. Since we don't know how much information
     * will need to be printed for the medicine, we need the location of the lowest
     * label on the panel to build of the rest.
     *
     * @param y     The y coordinate of the last label.
     */
    private void setLocations(int y){
        // Ensures there are the same number of labels as there are text boxes.
        assert (changes.size() == changeLabels.size());

        for (int i = 0; i < changes.size(); i++){
            changeLabels.get(i).setLocation(20, y + 20 + 140 * i);
            changes.get(i).setLocation(20, y + 80 + 140 * i);
        }

        for (JButton button: super.buttonResponses.keySet()){
            button.setLocation(43, y + 40 + 140 * changes.size());
        }

        panel.setPreferredSize(new Dimension(486, y + 160 + 140 * changes.size()));

        super.view.revalidate();
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
