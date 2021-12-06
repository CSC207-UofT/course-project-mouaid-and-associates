package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class CreateAccountWindow extends Window {
    /*
     * The window that displays the Create an Account page.
     */
    String[] userInput;
    JTextField name;
    JTextField userName;
    JTextField password;

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

        name = new JTextField("Name");       // A text box.
        name.setSize(100, 50);
        name.setLocation(100,100);
        userName = new JTextField("Username");
        userName.setSize(100, 50);
        userName.setLocation(100, 200);
        password = new JTextField("Password");
        password.setSize(100, 50);
        password.setLocation(100, 300);

        JButton submit = new JButton("Submit");
        submit.setSize(100, 100);
        submit.setLocation(100, 400);


        panel.add(name);
        panel.add(userName);
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
