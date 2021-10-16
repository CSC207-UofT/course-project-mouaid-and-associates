package frameworks_and_drivers;

import interface_adapters.Window;
import java.util.Scanner;
import java.io.Console;

public class LoginWindow implements Window {
    /*
     * THe window that displays the Login page.
     */

    //The user logs in.

    /**
     * Gets input from the user in order to login.
     * @return A list containing the user login input
     */
    @Override
    public String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
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
