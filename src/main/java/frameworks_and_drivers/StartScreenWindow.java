package frameworks_and_drivers;

import interface_adapters.Window;

import javax.swing.*;
import java.util.Scanner;

public class StartScreenWindow extends Window {
    /**
     * The window that displays the Start Screen page.
     */
    public StartScreenWindow(Scanner scanner) {
        super(scanner);
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
        // BoxLayout places things top to bottom, when you specify the Y_AXIS attribute
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);

        // Make the two buttons:
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
     * Notify the observer of a change
     *
     * @param frame     The frame from which we get our change
     * @param source    The source of the change, in this case a button.
     */
    @Override
    public void update(ObservableFrame frame, Object source) {
        if (super.buttonResponses.containsKey(source)){
            super.userResponded = true;
            userInput = new String[]{super.buttonResponses.get(source)};
        }
    }
}
