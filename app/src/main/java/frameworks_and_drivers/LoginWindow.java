package frameworks_and_drivers;

import interface_adapters.DisplayEntityInformation;
import interface_adapters.Window;

import java.util.Scanner;

public class LoginWindow extends Window implements DisplayEntityInformation {
    /*
     * The window that displays the Login page.
     */

    public LoginWindow(Scanner scanner) {
        super(scanner);
    }

    //The user logs in.

    /**
     * Gets input from the user in order to login.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        String[] returnList = new String[2];
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        returnList[0] = username;
        returnList[1] = password;
        return returnList;
        }

    /**
     * This is an interface that allows classes in frameworks_and_drivers to get information
     * from classes in the inner levels, and display that information.
     *
     * @param info      The information to be displayed.
     */
    @Override
    public void displayInfo(String[] info) {
        for (String line: info){
            System.out.println(line);
        }
    }
}
