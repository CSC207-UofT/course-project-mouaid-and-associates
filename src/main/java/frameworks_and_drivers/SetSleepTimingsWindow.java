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
    private List<String> userInput;
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
     * @param source
     */
    @Override
    public void update(Object source) {

    }

    @Override
    public void createView() {
        // Create a new JPanel
        panel = new JPanel();
        panel.setLayout(null);
        super.setPanelSize(panel);

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
