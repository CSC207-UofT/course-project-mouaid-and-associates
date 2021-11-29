package frameworks_and_drivers;

import interface_adapters.ObservableFrame;
import interface_adapters.Window;

import javax.swing.*;
import java.util.Scanner;

public class CreateAccountWindow extends Window {
    /*
     * The window that displays the Create an Account page.
     */

    public CreateAccountWindow(Scanner scanner, ObservableFrame frame) {
        super(scanner, frame);
        // Create the view for this window
        createView();
    }

    /**
     * Gets input from the user when first using the app in order to create an account.
     * @return A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        System.out.println("Name: ");
        String name = scanner.nextLine();


        System.out.println("Username:");
        String username = scanner.nextLine();

        System.out.println("Password:");
        String password = scanner.nextLine();

        String[] returnList = new String[3];
        returnList[0] = name;
        returnList[1] = username;
        returnList[2] = password;


        return returnList;

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
        // Our container for the components (like a board on which you pin things)
        JPanel panel = new JPanel();
        // The layout in which components are placed.
        // BoxLayout places things top to bottom, when you specify the Y_AXIS attribute
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JTextField name = new JTextField("Name");       // A text box.
        name.setSize(300, 200);
        JTextField userName = new JTextField("Username");
        name.setSize(300, 200);
        JTextField password = new JTextField("Password");
        name.setSize(300, 200);

        JButton submit = new JButton("Submit");
        submit.setSize(286, 200);

        super.buttonResponses.put(submit, "1");

    }

}
