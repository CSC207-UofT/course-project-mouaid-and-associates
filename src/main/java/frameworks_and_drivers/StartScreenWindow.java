package frameworks_and_drivers;

import interface_adapters.Window;

import java.io.*;

public class StartScreenWindow implements Window {

    private Console cnsl = System.console();

    /**
     * Gets user input and whether they decide to login or sign up
     * @return "0" or "1" representing whether they have decided to log in or sign up
     */
    public String[] getUserInput() {


         // returns 0 if the user selects login and returns 1 if the user selects signup

        String[] returnList = new String[1];
        while (true) {
            String input = cnsl.readLine("For Login type L, for Sign up type S");
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
