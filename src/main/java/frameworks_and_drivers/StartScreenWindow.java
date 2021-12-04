package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class StartScreenWindow extends Window {
    /**
     * The window that displays the Start Screen page.
     */
    String[] userInput;

    public StartScreenWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        // Create the view for this window.
        createView();
        userInput = new String[1];
    }

    /**
     * Gets user input and whether they decide to login or sign up
     * @return "0" or "1" representing whether they have decided to log in or sign up
     */
    public String[] getUserInput() {
         // returns 0 if the user selects login and returns 1 if the user selects signup
        return userInput;
    }

    /**
     * Create the view for the Window.
     */
    @Override
    public void createView() {
        // Our container for the components (like a board on which you pin things)
        JPanel panel = new JPanel();
        // The layout in which components are placed.
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);

        // Make the two buttons:
//        JLabel label = new JLabel("Message");     // A text label for printing out information.
        JButton login = new JButton("LOGIN");
        login.setSize(286, 200);        // Set the size
        login.setLocation(100, 100);           // Set the location

        JButton signUp = new JButton("SIGN UP");
        signUp.setSize(286, 200);
        signUp.setLocation(100, 500);

        // Add the two buttons to the super buttonResponse map
        super.buttonResponses.put(login, "0");
        super.buttonResponses.put(signUp, "1");

        // createVerticalGlue adds space between the frame or panel border and the component.
        panel.add(login);

        panel.add(signUp);

        // Make sure the buttons have action listeners.
        super.addActionListenerToAllButtons();

        // Sets the view for this Window.
        super.view = panel;
    }

    /**
     * Checks if a button on this view is the source of the event (i.e. A button has
     * been pressed from this view) and performs the appropriate actions.
     *
     * @param source    The source of the change, in this case a button.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            userInput[0] = super.buttonResponses.get((JButton) source);
            super.userResponded = true;
        }
    }
}
