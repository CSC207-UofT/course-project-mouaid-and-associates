package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;
import java.util.Scanner;

public class CreateAccountWindow implements Window {
    /*
     * The window that displays the Create an Account page.
     */

    /**
     * Gets input from the user when first using the app in order to create an account.
     * @return A list containing the user input
     */
    @Override
    public String[] getUserInput() {
        Scanner scanner = new Scanner(System.in);
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

}
