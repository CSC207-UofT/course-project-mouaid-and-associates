package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Scanner;

public class CreateAccountWindow extends Window {
    /*
     * The window that displays the Create an Account page.
     */
    String[] userInput;
    JTextField name;
    JTextField userName;
    JPasswordField password;

    public CreateAccountWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        // Create the view for this window
        createView();

        userInput = new String[3];
    }

    /**
     * Gets input from the user when first using the app in order to create an account.
     * @return A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        return userInput;

    }


    @Override
    public void createView() {
        // Our container for the components (like a board on which you pin things)
        JPanel panel = new JPanel();
        // The layout in which components are placed.
        panel.setLayout(null);

        // Set the size of the panel.
        super.setPanelSize(panel);
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setSize(286, 40);
        nameLabel.setLocation(43, 50);
        name = new JTextField();       // A text box.
        name.setSize(400, 50);
        name.setLocation(43,100);
        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setSize(286, 40);
        usernameLabel.setLocation(43, 150);
        userName = new JTextField();
        userName.setSize(400, 50);
        userName.setLocation(43, 200);
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setSize(286, 40);
        passwordLabel.setLocation(43, 250);
        password = new JPasswordField();
        password.setSize(400, 50);
        password.setLocation(43, 300);

        JButton submit = new JButton("Submit");
        submit.setSize(400, 100);
        submit.setLocation(43, 400);


        panel.add(nameLabel);
        panel.add(name);
        panel.add(usernameLabel);
        panel.add(userName);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(submit);



        super.buttonResponses.put(submit, "1");
        super.view = panel;

        super.addActionListenerToAllButtons();

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
            super.userResponded = true;
            userInput[0] = name.getText();
            userInput[1] = userName.getText();
            userInput[2] = password.getText();

        }

    }

}
