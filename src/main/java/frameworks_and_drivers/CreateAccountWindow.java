package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.Console;
import java.util.Scanner;

public class CreateAccountWindow implements Window {

    Console cnsl = System.console();
    //Creates an account for a user.
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
