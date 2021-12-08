package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Scanner;


public class LoginWindow extends Window implements DisplayEntityInformation {
    /**
     * The window that displays the Login page.
     *
     * Instance Attributes:
     *  - userInput: The user's input, stored in an array.
     *  - username: A text box for the user to enter their username.
     *  - password: A text box for the user to enter their password.
     *  - errorMessage: An error message to show when the password is wrong.
     */
    private String[] userInput;
    private JTextField username;
    private JPasswordField password;
    private JLabel errorMessage;

    public LoginWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        createView();
        userInput = new String[2];
    }

    /**
     * Gets input from the user in order to login.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        return userInput;
    }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info      The information to be displayed.
     */
    @Override
    public void displayInfo(String[] info) {
        // In this case, there is an error message, which means that the info is
        // a string of size 1.

        // Set the error message to the one passed in.
        JOptionPane.showMessageDialog(super.frame, "Password or Username is wrong!",
                "Incorrect Information",
                JOptionPane.INFORMATION_MESSAGE);

        super.userResponded = false;
    }

    /**
     * Checks to see if the button that was clicked was in this window.
     *
     * @param source        The button from which the change occurred.
     */
    @Override
    public void update(Object source) {
        if (super.buttonResponses.containsKey((JButton) source)){
            // Get the user's username and password if the button that was clicked
            // is from this window.
            userInput[0] = username.getText();
            userInput[1] = String.valueOf(password.getPassword());
            super.userResponded = true;
        }
    }

    /**
     * Creates the view for this window.
     */
    @Override
    public void createView() {
        // Create a new panel
        JPanel panel = new JPanel();

        // Required for us to use pixel measurements.
        panel.setLayout(null);

        // Set the panel size.
        super.setPanelSize(panel);

        // Instantiate the two text boxes
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setSize(286, 40);
        usernameLabel.setLocation(100, 50);
        username = new JTextField();
        username.setSize(286, 50);
        username.setLocation(100, 100);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setSize(286, 40);
        passwordLabel.setLocation(100, 250);
        password = new JPasswordField();
        password.setSize(286, 50);
        password.setLocation(100, 300);

        // Instantiate the LOGIN Button
        JButton login = new JButton("LOGIN");
        login.setLocation(100, 500);
        login.setSize(100, 70);

        // Instantiate the error message:
        errorMessage = new JLabel("");
        errorMessage.setLocation(100, 30);
        errorMessage.setSize(200, 50);

        // Add the items to the panel
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(login);
        panel.add(errorMessage);

        // Add the button to the map of button to responses.
        super.buttonResponses.put(login, "0");

        // Add an action listener for each button.
        super.addActionListenerToAllButtons();

        // Set the view for this window.
        super.view = panel;

    }
}
