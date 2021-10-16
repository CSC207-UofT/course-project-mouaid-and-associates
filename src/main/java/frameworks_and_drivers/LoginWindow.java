package frameworks_and_drivers;

import interface_adapters.Window;
import java.util.Scanner;
import java.io.Console;

public class LoginWindow extends Window {
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

    }
