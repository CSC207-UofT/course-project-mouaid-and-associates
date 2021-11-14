package frameworks_and_drivers;

import interface_adapters.Window;
import java.util.Scanner;

public class StartScreenWindow extends Window {
    /**
     * The window that displays the Start Screen page.
     */
    public StartScreenWindow(Scanner scanner) {
        super(scanner);
    }

    /**
     * Gets user input and whether they decide to login or sign up
     * @return "0" or "1" representing whether they have decided to log in or sign up
     */
    public String[] getUserInput() {
         // returns 0 if the user selects login and returns 1 if the user selects signup
        String[] returnList = new String[1];

        while (true) {
            System.out.println("For Login type L, for Sign up type S");
            String input = scanner.nextLine();
            if (input.equals("L")) {
                returnList[0] = "0";
                return returnList;
            } else if (input.equals("S")) {
                returnList[0] = "1";
                return returnList;
            }
        }
    }

}
