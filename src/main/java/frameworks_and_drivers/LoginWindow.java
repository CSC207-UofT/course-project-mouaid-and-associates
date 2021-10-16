package frameworks_and_drivers;

import interface_adapters.Window;
import java.util.Scanner;
import java.io.Console;

public class LoginWindow implements Window {

    Console cnsl = System.console();
    //The user logs in.
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
